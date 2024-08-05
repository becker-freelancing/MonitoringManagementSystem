package com.jabasoft.mms.documentmanagement.api;

import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import com.jabasoft.mms.documentmanagement.dto.FilePathDto;
import com.jabasoft.mms.documentmanagement.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface DocumentManagementPort {

	public Optional<DocumentDto> saveDocument(DocumentDto document);

	public List<DocumentDto> getAllDocuments();

	public Optional<DocumentDto> getDocument(Long documentId);

	public Optional<DocumentWithoutContentDto> deleteDocument(FilePathDto path, String name);

    boolean existsDocument(DocumentWithoutContentDto document);

	public Optional<DocumentDto> setCustomer(Long documentId, Long customerId);

	public Optional<DocumentDto> resetCustomer(Long documentId);

	public Optional<DocumentDto> addTag(Long documentId, TagDto tag);

	public Optional<DocumentDto> removeTag(Long documentId, TagDto tag);
}
