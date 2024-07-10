package com.jabasoft.mms.documentmanagement.api;

import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import com.jabasoft.mms.documentmanagement.dto.FilePathDto;

import java.util.List;
import java.util.Optional;

public interface DocumentManagementPort {

	public Optional<DocumentDto> saveDocument(DocumentDto document);

	public List<DocumentDto> getAllDocuments();

	public Optional<DocumentDto> getDocument(Long documentId);

	public Optional<DocumentWithoutContentDto> deleteDocument(FilePathDto path, String name);

    boolean existsDocument(DocumentWithoutContentDto document);
}
