package com.jabasoft.mms.documentmanagement.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.documentmanagement.domain.service.filetype.FileTypeUtilImpl;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import com.jabasoft.mms.settings.api.SettingsPort;

@Component
public class FileDocumentDao implements DocumentRepository {

	private Path documentRootPath;

	@Autowired
	public FileDocumentDao(SettingsPort settingsPort) {

		this.documentRootPath = settingsPort.getLocalDocumentsRootPath();
	}

	@Override
	public List<Document> getAllDocuments() {

		try (Stream<Path> walk = Files.walk(documentRootPath)) {
			return walk.filter(p -> !Files.isDirectory(p)).map(this::readDocumentFromPath).toList();
		}
		catch (IOException e) {
			return new ArrayList<>();
		}
	}

	private Document readDocumentFromPath(Path p) {

		byte[] content;
		try {
			content = Files.readAllBytes(p);
		}
		catch (IOException e) {
			return null;
		}

		Document document = new Document();
		document.setContent(content);
		document.setDocumentName(parseDocumentName(p));
		document.setFileType(parseFileType(p));
		document.setPathToDocumentFromRoot(parsePathFromRoot(p));

		return document;
	}

	private FilePath parsePathFromRoot(Path p) {

		String fromRoot = p.toAbsolutePath().toString().replace(documentRootPath.toString(), "");
		fromRoot = fromRoot.substring(0, fromRoot.lastIndexOf("\\") + 1);

		return new FilePath(fromRoot);
	}

	private FileType parseFileType(Path path){
		String pathString = path.toString();
		String fileName = pathString.substring(pathString.lastIndexOf("\\"));

		String fileType = fileName.split("\\.")[1];

		return new FileTypeUtilImpl().fromString(fileType);
	}

	private String parseDocumentName(Path path){

		String pathString = path.toString();
		String fileName = pathString.substring(pathString.lastIndexOf("\\"));

		return fileName.split("\\.")[0].substring(1);
	}

	@Override
	public Optional<Document> getDocument(Long documentId) {

		return Optional.empty();
	}

	@Override
	public Optional<Document> saveDocument(Document document) {

		try {
			Path writePath = Path.of(documentRootPath.toString(), document.getPathToDocumentFromRoot().getFilePath(), document.getDocumentName() + "." + document.getFileType().getFileEnding());
			Path parentDir = writePath.getParent();

			if(!Files.exists(parentDir)){
				Files.createDirectories(parentDir);
			}

			if(Files.exists(writePath)){
				Files.delete(writePath);
			}
			Files.createFile(writePath);
			Files.write(writePath, document.getContent(), StandardOpenOption.WRITE);

			return Optional.of(document);
		}
		catch (IOException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Document> deleteDocument(FilePath path, String name) {

		try{
			Path filePath = Path.of(documentRootPath.toString(), path.getFilePath());
			int filePathLength = filePath.toString().split("\\\\").length + 1;

			List<Path> toDelete = Files.walk(filePath)
				.filter(p -> p.toString().split("\\\\").length == filePathLength)
				.filter(p -> !Files.isDirectory(p))
				.filter(p -> parseDocumentName(p).equals(name))
				.collect(Collectors.toList());

			if(toDelete.size() != 1){
				return Optional.empty();
			}

			Document document = readDocumentFromPath(toDelete.get(0));
			Files.delete(toDelete.get(0));

			return Optional.ofNullable(document);
		}
		catch (IOException e) {
			return Optional.empty();
		}
	}

}
