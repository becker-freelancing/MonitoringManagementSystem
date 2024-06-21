package com.jabasoft.mms.documentmanagement.domain.service.filestructure;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FilePathWithDocument;
import com.jabasoft.mms.documentmanagement.domain.model.FileStructure;
import com.jabasoft.mms.documentmanagement.domain.model.FileStructureWithDocuments;

import java.util.Optional;

public interface FileStructureService {
    FileStructureWithDocuments addDocument(FileStructureWithDocuments fileStructure, FilePathWithDocument filePathWithDocument);

    FileStructure addFilePath(FileStructure fileStructure, FilePath toAdd);

    Optional<FileStructure> getChildrenByName(FileStructure fileStructure, String name);

    Optional<FileStructureWithDocuments> getChildrenByName(FileStructureWithDocuments fileStructure, String name);
}
