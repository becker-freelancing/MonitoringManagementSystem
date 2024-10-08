package com.jabasoft.mms.documentmanagement.filepath.api;

import com.jabasoft.mms.documentmanagement.dto.FilePathDto;
import com.jabasoft.mms.documentmanagement.dto.FilePathWithDocumentDto;
import com.jabasoft.mms.documentmanagement.dto.FileStructureDto;
import com.jabasoft.mms.documentmanagement.dto.FileStructureWithDocumentsDto;
import com.jabasoft.mms.documentmanagement.error.ApiFileModificationException;

import java.util.Optional;
import java.util.Set;

public interface FilePathManagementPort {

	public Optional<FileStructureDto> getFileStructure();

	public Optional<FileStructureWithDocumentsDto> getFileStructureWithDocuments();

	public Set<FilePathWithDocumentDto> findAllChildrenFromPath(FilePathDto filePathDto);

	public Optional<FileStructureDto> createFileStructure(FilePathDto path) throws ApiFileModificationException;

	public Optional<FileStructureDto> deleteFileStructure(FilePathDto path) throws ApiFileModificationException;

	public Optional<FileStructureDto> deleteFileStructureWithChildren(FilePathDto pathDto) throws ApiFileModificationException;
}
