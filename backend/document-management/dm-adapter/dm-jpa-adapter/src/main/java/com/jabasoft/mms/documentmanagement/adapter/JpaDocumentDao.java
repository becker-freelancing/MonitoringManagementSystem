package com.jabasoft.mms.documentmanagement.adapter;

import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.entity.JpaDocument;
import com.jabasoft.mms.documentmanagement.entity.JpaTag;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaDocumentDao implements DocumentRepository {

	private SpringJpaDocumentRepository documentRepository;

	@Autowired
	public JpaDocumentDao(SpringJpaDocumentRepository documentRepository) {

		this.documentRepository = documentRepository;
	}

	@Override
	public List<Document> getAllDocuments() {

		Iterable<JpaDocument> jpaDocuments = documentRepository.findAll();

		List<Document> documents = new ArrayList<>();

		for (JpaDocument jpaDocument : jpaDocuments) {
			if(jpaDocument.getContent() != null) {
				documents.add(map(jpaDocument));
			}
		}

		return documents;
	}

	@Override
	public Optional<Document> getDocument(Long documentId) {

		 Optional<JpaDocument> byId = documentRepository.findById(documentId);

		return byId.map(this::map);
	}

	@Override
	public Optional<Document> saveDocument(Document document) {

		if (document == null){
			return Optional.empty();
		}

		JpaDocument saved = documentRepository.save(map(document));

		return Optional.of(saved).map(this::map);
	}

	@Override
	public Optional<Document> deleteDocument(FilePath path, String name) {

		Optional<JpaDocument> toDelete = documentRepository.findByPathToDocumentFromRootAndDocumentName(path.getFilePath(), name);

		if(toDelete.isEmpty()){
			return Optional.empty();
		}

		documentRepository.deleteById(toDelete.get().getDocumentId());

		return toDelete.map(this::map);
	}

	@Override
	public boolean existsDocument(DocumentWithoutContent map) {
		Optional<JpaDocument> toDelete = documentRepository.findByPathToDocumentFromRootAndDocumentName(map.getPathToDocumentFromRoot().getFilePath(), map.getDocumentName());

		return toDelete.isPresent();
	}

    @Override
    public Optional<Document> setCustomer(Long documentId, Long customerId) {
        Optional<JpaDocument> optionalDocument = documentRepository.findById(documentId);

        if (optionalDocument.isEmpty()) {
            return Optional.empty();
        }

        JpaDocument jpaDocument = optionalDocument.get();

        jpaDocument.setCustomerId(customerId);

        JpaDocument saved = documentRepository.save(jpaDocument);

        return Optional.of(saved).map(this::map);
    }

    @Override
    public Optional<Document> resetCustomer(Long documentId) {
        return setCustomer(documentId, null);
    }

	private JpaDocument map(Document document) {

		JpaDocument jpaDocument = new JpaDocument();
		jpaDocument.setDocumentId(document.getDocumentId());
		jpaDocument.setDocumentName(document.getDocumentName());
		jpaDocument.setContent(document.getContent());
		jpaDocument.setFileType(document.getFileType().getFileEnding());
		jpaDocument.setPathToDocumentFromRoot(document.getPathToDocumentFromRoot().getFilePath());
        jpaDocument.setCustomerId(document.getCustomerId());
        jpaDocument.setTags(document.getTags().stream().map(this::map).collect(Collectors.toSet()));

		return jpaDocument;
	}

	private Document map(JpaDocument jpaDocument) {


		Document document = new Document();
		document.setDocumentId(jpaDocument.getDocumentId());
		document.setDocumentName(jpaDocument.getDocumentName());
		document.setContent(jpaDocument.getContent());
		document.setFileType(new FileType(jpaDocument.getFileType()));
		document.setPathToDocumentFromRoot(new FilePath(jpaDocument.getPathToDocumentFromRoot()));
        document.setCustomerId(jpaDocument.getCustomerId());
        document.setTags(jpaDocument.getTags().stream().map(this::map).collect(Collectors.toSet()));

		return document;
	}

    private Tag map(JpaTag jpaTag) {
        return new Tag(jpaTag.getTag());
    }

    private JpaTag map(Tag tag) {
        JpaTag jpaTag = new JpaTag();
        jpaTag.setTag(tag.getTagName());

        return jpaTag;
    }
}
