package com.jabasoft.mms.documentmanagement.api;

import java.util.List;
import java.util.Set;

import com.jabasoft.mms.documentmanagement.api.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.api.dto.GetDocumentDto;

public interface DocumentManagementPort {

	public String  saveDocument(DocumentDto document);

	public List<GetDocumentDto> getAllDocuments();

	public GetDocumentDto getDocument(String documentId);

}
