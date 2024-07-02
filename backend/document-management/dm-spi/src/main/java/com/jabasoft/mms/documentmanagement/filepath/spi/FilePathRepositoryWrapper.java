package com.jabasoft.mms.documentmanagement.filepath.spi;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FilePathWithDocument;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilePathRepositoryWrapper implements FilePathRepository{


    private List<FilePathRepository> filePathRepositories;
    private FilePathRepository defaultFilePathRepository;

    public FilePathRepositoryWrapper(List<FilePathRepository> filePathRepositories, FilePathRepository defaultFilePathRepository) {
        if(filePathRepositories.isEmpty()){
            throw new IllegalArgumentException("Min one FilePathRepository needed");
        }
        if(filePathRepositories.stream().map(FilePathRepository::getClass).collect(Collectors.toSet()).contains(defaultFilePathRepository.getClass())){
            throw new IllegalArgumentException("FilePathRepositories can not contain the default FilePathRepository");
        }
        this.filePathRepositories = filePathRepositories;
        this.defaultFilePathRepository = defaultFilePathRepository;
    }

    @Override
    public Set<FilePath> findAllPaths() {
        return defaultFilePathRepository.findAllPaths();
    }

    @Override
    public Set<FilePathWithDocument> findAllPathsWithDocuments() {
        return defaultFilePathRepository.findAllPathsWithDocuments();
    }

    @Override
    public boolean isPathCreatable(FilePath path) {
        return defaultFilePathRepository.isPathCreatable(path);
    }

    @Override
    public boolean hasPathChildren(FilePath path) {
        return defaultFilePathRepository.hasPathChildren(path);
    }

    @Override
    public void createFileStructure(FilePath path) throws FileModificationException {
        for (FilePathRepository filePathRepository : filePathRepositories) {
            filePathRepository.createFileStructure(path);
        }
        defaultFilePathRepository.createFileStructure(path);
    }

    @Override
    public void deleteFileStructure(FilePath path) throws FileModificationException {
        for (FilePathRepository filePathRepository : filePathRepositories) {
            filePathRepository.deleteFileStructure(path);
        }
        defaultFilePathRepository.deleteFileStructure(path);
    }

    @Override
    public void deleteFileStructureWithChildren(FilePath path) throws FileModificationException {
        for (FilePathRepository filePathRepository : filePathRepositories) {
            filePathRepository.deleteFileStructureWithChildren(path);
        }
        defaultFilePathRepository.deleteFileStructureWithChildren(path);
    }

    @Override
    public Set<FilePathWithDocument> findAllChildrenFromPath(FilePath path) {

        return defaultFilePathRepository.findAllChildrenFromPath(path);
    }
}
