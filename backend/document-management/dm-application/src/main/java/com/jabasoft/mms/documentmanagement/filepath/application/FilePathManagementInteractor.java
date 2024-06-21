package com.jabasoft.mms.documentmanagement.filepath.application;

import com.jabasoft.mms.documentmanagement.dto.*;
import com.jabasoft.mms.documentmanagement.filepath.api.FilePathManagementPort;
import com.jabasoft.mms.documentmanagement.error.ApiFileModificationException;
import com.jabasoft.mms.documentmanagement.error.FileModificationExceptionReasonDto;
import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import com.jabasoft.mms.documentmanagement.domain.service.filepath.FilePathService;
import com.jabasoft.mms.documentmanagement.domain.service.filestructure.FileStructureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class FilePathManagementInteractor implements FilePathManagementPort {

    private FilePathRepository repository;
    private FilePathService filePathService;

    @Autowired
    public FilePathManagementInteractor(FilePathRepository repository, FilePathService filePathService) {
        this.repository = repository;
        this.filePathService = filePathService;
    }

    @Override
    public Optional<FileStructureDto> getFileStructure() {
        Set<FilePath> allPaths = repository.findAllPaths();

        if (allPaths.isEmpty()) {
            return Optional.empty();
        }

        FileStructure fileStructure = toFileStructure(allPaths);
        return Optional.of(map(fileStructure));
    }

    @Override
    public Optional<FileStructureWithDocumentsDto> getFileStructureWithDocuments() {

        Set<FilePathWithDocument> pathsWithDocuments = repository.findAllPathsWithDocuments();

        if (pathsWithDocuments.isEmpty()) {
            return Optional.empty();
        }

        FileStructure fileStructure = toFileStructure(pathsWithDocuments);
        FileStructureWithDocuments fileStructureWithDocuments = toFileStructureWithDocuments(fileStructure, pathsWithDocuments);
        return Optional.of(map(fileStructureWithDocuments));
    }

    @Override
    public Optional<FileStructureDto> createFileStructure(FilePathDto path) throws ApiFileModificationException {

        try {
            FilePath mappedPath = map(path);

            if (repository.isPathCreatable(mappedPath)) {
                repository.createFileStructure(mappedPath);
                return getFileStructure();
            } else {
                return Optional.empty();
            }

        } catch (FileModificationException e) {
            FileModificationExceptionReasonDto reason = mapEnumByName(FileModificationExceptionReasonDto.class, e.getReason());
            throw new ApiFileModificationException(reason);
        }

    }

    @Override
    public Optional<FileStructureDto> deleteFileStructure(FilePathDto path) throws ApiFileModificationException {

        try {
            FilePath mappedPath = map(path);

            if (!repository.hasPathChildren(mappedPath)) {
                repository.deleteFileStructure(mappedPath);
                return getFileStructure();
            } else {
                return Optional.empty();
            }

        } catch (FileModificationException e) {
            FileModificationExceptionReasonDto reason = mapEnumByName(FileModificationExceptionReasonDto.class, e.getReason());
            throw new ApiFileModificationException(reason);
        }
    }

    @Override
    public Optional<FileStructureDto> deleteFileStructureWithChildren(FilePathDto pathDto) throws ApiFileModificationException {
        try {
            FilePath mappedPath = map(pathDto);

            repository.deleteFileStructureWithChildren(mappedPath);

            return getFileStructure();

        } catch (FileModificationException e) {
            FileModificationExceptionReasonDto reason = mapEnumByName(FileModificationExceptionReasonDto.class, e.getReason());
            throw new ApiFileModificationException(reason);
        }
    }

    private FileStructure toFileStructure(Set<? extends FilePath> filePaths) {
        FileStructureServiceImpl fileStructureService = new FileStructureServiceImpl();

        FileStructure fileStructure = new FileStructure("root");

        for (FilePath filePath : filePaths) {
            fileStructure = fileStructureService.addFilePath(fileStructure, filePath);
        }
        return fileStructure;
    }



    private FileStructureWithDocuments toFileStructureWithDocuments(FileStructure fileStructure, Set<FilePathWithDocument> pathsWithDocuments) {

        FileStructureServiceImpl fileStructureService = new FileStructureServiceImpl();
        FileStructureWithDocuments fileStructureWithDocuments = new FileStructureWithDocuments(fileStructure);

        for (FilePathWithDocument pathWithDocument : pathsWithDocuments) {
            fileStructureService.addDocument(fileStructureWithDocuments, pathWithDocument);
        }

        return fileStructureWithDocuments;
    }

    private FileStructureDto map(FileStructure fileStructure) {
        FileStructureDto fileStructureDto = new FileStructureDto();
        fileStructureDto.setCurrent(fileStructure.getCurrent());

        if (fileStructure.getChildren().isEmpty()) {
            return fileStructureDto;
        }

        for (FileStructure child : fileStructure.getChildren()) {
            fileStructureDto.addChildren(map(child));
        }

        return fileStructureDto;
    }

    private FilePath map(FilePathDto filePathDto) {
        FilePath filePath = new FilePath();
        filePath.setFilePath(filePathDto.getFilePath());
        return filePath;
    }

    private FilePathDto map(FilePath filePath) {
        FilePathDto filePathDto = new FilePathDto();
        filePathDto.setFilePath(filePath.getFilePath());
        return filePathDto;
    }



    private FileStructureWithDocumentsDto map(FileStructureWithDocuments fileStructure) {
        FileStructureWithDocumentsDto fileStructureDto = new FileStructureWithDocumentsDto();
        fileStructureDto.setCurrent(fileStructure.getCurrent());

        for (DocumentWithoutContent document : fileStructure.getDocuments()) {
            fileStructureDto.addDocument(map(document));
        }


        for (FileStructureWithDocuments child : fileStructure.getChildren()) {
            fileStructureDto.addChildren(map(child));
        }

        return fileStructureDto;
    }

    private DocumentWithoutContentDto map(DocumentWithoutContent document) {
        DocumentWithoutContentDto documentWithoutContentDto = new DocumentWithoutContentDto();

        documentWithoutContentDto.setDocumentId(document.getDocumentId());
        documentWithoutContentDto.setFileType(mapEnumByName(FileTypeDto.class, document.getFileType()));
        documentWithoutContentDto.setDocumentName(document.getDocumentName());
        documentWithoutContentDto.setPathToDocumentFromRoot(map(document.getPathToDocumentFromRoot()));

        return documentWithoutContentDto;
    }

    private <S extends Enum<S>, T extends Enum<T>> T mapEnumByName(Class<T> targetEnumType, S sourceEnum) {

        if (sourceEnum == null) {
            return null;
        }

        return Enum.valueOf(targetEnumType, sourceEnum.name());
    }
}
