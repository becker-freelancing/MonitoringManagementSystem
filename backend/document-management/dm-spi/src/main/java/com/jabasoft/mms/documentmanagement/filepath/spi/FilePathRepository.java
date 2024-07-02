package com.jabasoft.mms.documentmanagement.filepath.spi;

import java.util.Set;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FilePathWithDocument;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;

public interface FilePathRepository {

	public Set<FilePath> findAllPaths();

	public Set<FilePathWithDocument> findAllPathsWithDocuments();

	public boolean isPathCreatable(FilePath path);

	public boolean hasPathChildren(FilePath path);

	public void createFileStructure(FilePath path)  throws FileModificationException;

	public void deleteFileStructure(FilePath path) throws FileModificationException;

	public void deleteFileStructureWithChildren(FilePath path) throws FileModificationException;

    Set<FilePathWithDocument> findAllChildrenFromPath(FilePath path);
}
