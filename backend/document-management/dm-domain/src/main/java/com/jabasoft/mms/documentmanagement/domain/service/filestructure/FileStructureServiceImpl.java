package com.jabasoft.mms.documentmanagement.domain.service.filestructure;

import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FilePathDoesNotExistException;
import com.jabasoft.mms.documentmanagement.domain.service.filepath.FilePathServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileStructureServiceImpl implements FileStructureService {

    @Override
    public FileStructureWithDocuments addDocument(FileStructureWithDocuments fileStructure, FilePathWithDocument filePathWithDocument){
        FilePathServiceImpl filePathService = new FilePathServiceImpl();

        List<FilePath> splitedFilePath = filePathService.splitPath(filePathWithDocument);

        FileStructureWithDocuments parent = fileStructure;

        for (FilePath subPath : splitedFilePath) {
            if(parent.getCurrent().equals(subPath.getFilePath())){
                continue;
            }
            Optional<FileStructureWithDocuments> children = getChildrenByName(parent, subPath.getFilePath());
            if(children.isPresent()){
                parent = children.get();
            } else {
                throw new FilePathDoesNotExistException(filePathWithDocument);
            }
        }

        parent.addDocument(filePathWithDocument.getDocument());

        return fileStructure;
    }

    @Override
    public FileStructure addFilePath(FileStructure fileStructure, FilePath toAdd){
        FilePathServiceImpl filePathService = new FilePathServiceImpl();

        List<FilePath> splitedPath = filePathService.splitPath(toAdd);

        FileStructure parent = fileStructure;

        for (FilePath toAddSubPath : splitedPath) {
            if(parent.getCurrent().equals(toAddSubPath.getFilePath())){
                continue;
            }
            Optional<FileStructure> children = getChildrenByName(parent, toAddSubPath.getFilePath());
            if(children.isPresent()){
                parent = children.get();
            } else {
                FileStructure child = new FileStructure(toAddSubPath.getFilePath());
                parent.addChildren(child);
                parent = child;
            }
        }

        return fileStructure;
    }

    @Override
    public Optional<FileStructure> getChildrenByName(FileStructure fileStructure, String name){
        for (FileStructure child : fileStructure.getChildren()) {
            if(child.getCurrent().equals(name)){
                return Optional.of(child);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<FileStructureWithDocuments> getChildrenByName(FileStructureWithDocuments fileStructure, String name){
        for (FileStructureWithDocuments child : fileStructure.getChildren()) {
            if(child.getCurrent().equals(name)){
                return Optional.of(child);
            }
        }
        return Optional.empty();
    }
}
