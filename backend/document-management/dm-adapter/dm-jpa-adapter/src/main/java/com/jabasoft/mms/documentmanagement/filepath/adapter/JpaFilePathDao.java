package com.jabasoft.mms.documentmanagement.filepath.adapter;

import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationExceptionReason;
import com.jabasoft.mms.documentmanagement.entity.JpaDocument;
import com.jabasoft.mms.documentmanagement.entity.JpaDocumentWithoutContent;
import com.jabasoft.mms.documentmanagement.entity.JpaTag;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JpaFilePathDao implements FilePathRepository {

	private SpringJpaFilePathRepository filePathRepository;

	@Autowired
	public JpaFilePathDao(SpringJpaFilePathRepository filePathRepository) {

		this.filePathRepository = filePathRepository;
	}

	@Override
	public Set<FilePath> findAllPaths() {

		return filePathRepository.findAllPathsFromDocumentRootPath().stream()
				.map(this::map)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<FilePathWithDocument> findAllPathsWithDocuments() {
		Set<JpaDocumentWithoutContent> allPathsWithDocuments = filePathRepository.findAllPathsWithDocuments();

		return allPathsWithDocuments.stream()
				.map(this::map)
				.map(doc -> new FilePathWithDocument(doc.getPathToDocumentFromRoot().getFilePath(), doc))
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isPathCreatable(FilePath path) {
		return !filePathRepository.existsByPathToDocumentFromRoot(path.getFilePath());
	}

	@Override
	public boolean hasPathChildren(FilePath path) {

		Set<JpaDocumentWithoutContent> subPaths = filePathRepository.findPathLike(path.getFilePath() + "\\\\%");
		Set<JpaDocumentWithoutContent> exactPaths = filePathRepository.findPathLike(path.getFilePath() + "%");

		subPaths.addAll(exactPaths);

		return subPaths.stream().anyMatch(doc -> !path.getFilePath().equals(doc.getPathToDocumentFromRoot()) ||
				(path.getFilePath().equals(doc.getPathToDocumentFromRoot()) && doc.getDocumentName() != null));
	}

	@Override
	public void createFileStructure(FilePath path) throws FileModificationException {

		JpaDocument jpaDocument = new JpaDocument();
		jpaDocument.setPathToDocumentFromRoot(path.getFilePath());

		filePathRepository.save(jpaDocument);
	}

	@Override
	public void deleteFileStructure(FilePath path) throws FileModificationException {
		if(hasPathChildren(path)){
			throw new FileModificationException(FileModificationExceptionReason.FILE_HAS_CHILDREN);
		}

		filePathRepository.deleteByPathToDocumentFromRoot(path.getFilePath());
	}

	@Override
	public void deleteFileStructureWithChildren(FilePath path) {
		filePathRepository.deleteAllByPathToDocumentFromRootStartsWith(path.getFilePath() + "\\");
		filePathRepository.deleteAllByPathToDocumentFromRoot(path.getFilePath());
	}

	@Override
	public Set<FilePathWithDocument> findAllChildrenFromPath(FilePath path) {
		Set<JpaDocumentWithoutContent> subPaths = filePathRepository.findPathLike(path.getFilePath() + "\\%");
		Set<JpaDocumentWithoutContent> exactPaths = filePathRepository.findPathLike(path.getFilePath() + "%");

		int filePathDepth = path.getFilePath().split("\\\\").length + 1;
		subPaths = subPaths.stream()
				.filter(doc -> doc.getPathToDocumentFromRoot().split("\\\\").length == filePathDepth)
				.peek(doc -> {
					doc.setDocumentId(null);
					doc.setFileType(null);
					doc.setDocumentName(null);
				})
				.collect(Collectors.toSet());

		exactPaths = exactPaths.stream()
				.filter(doc -> path.getFilePath().equals(doc.getPathToDocumentFromRoot()))
				.filter(doc -> doc.getDocumentName() != null).collect(Collectors.toSet());

		exactPaths.addAll(subPaths);

		return exactPaths.stream()
				.map(this::mapToFilePathWithDocument)
				.collect(Collectors.toSet());
	}

	private FilePathWithDocument mapToFilePathWithDocument(JpaDocumentWithoutContent jpaDocumentWithoutContent){
		if(jpaDocumentWithoutContent.getDocumentName() == null){
			return new FilePathWithDocument(jpaDocumentWithoutContent.getPathToDocumentFromRoot(), null);
		}

		return new FilePathWithDocument(jpaDocumentWithoutContent.getPathToDocumentFromRoot(), map(jpaDocumentWithoutContent));
	}

	private FilePath map(String path){

		return new FilePath(path);
	}

	private DocumentWithoutContent map(JpaDocumentWithoutContent jpaDocument){

		DocumentWithoutContent document = new DocumentWithoutContent();
		document.setDocumentId(jpaDocument.getDocumentId());
		document.setDocumentName(jpaDocument.getDocumentName());
		document.setFileType(new FileType(jpaDocument.getFileType()));
		document.setPathToDocumentFromRoot(new FilePath(jpaDocument.getPathToDocumentFromRoot()));
		document.setTags(jpaDocument.getTags().stream().map(this::map).collect(Collectors.toSet()));

		return document;
	}


	private Tag map(JpaTag jpaTag) {
		return new Tag(jpaTag.getTag());
	}
}
