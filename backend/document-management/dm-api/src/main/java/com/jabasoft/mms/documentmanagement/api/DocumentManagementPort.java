package com.jabasoft.mms.documentmanagement.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.FilePathDto;

public interface DocumentManagementPort {

	public Optional<DocumentDto> saveDocument(DocumentDto document);

	public List<DocumentDto> getAllDocuments();

	public Optional<DocumentDto> getDocument(Long documentId);

	public Optional<DocumentDto> deleteDocument(FilePathDto path, String name);
}
