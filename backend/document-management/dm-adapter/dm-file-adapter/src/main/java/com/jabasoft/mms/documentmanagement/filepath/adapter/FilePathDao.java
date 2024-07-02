package com.jabasoft.mms.documentmanagement.filepath.adapter;

import com.jabasoft.mms.documentmanagement.domain.model.DocumentWithoutContent;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FilePathWithDocument;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import com.jabasoft.mms.documentmanagement.domain.service.filetype.FileTypeUtilImpl;
import com.jabasoft.mms.settings.api.SettingsPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FilePathDao implements FilePathRepository {

    Path root;

    @Autowired
    public FilePathDao(SettingsPort settingsPort) {
        this.root = settingsPort.getLocalDocumentsRootPath();
    }

    @Override
    public Set<FilePath> findAllPaths() {
        try (Stream<Path> walk = Files.walk(root)) {
            return walk.filter(Files::isDirectory)
                    .filter(p -> !p.equals(root))
                    .map(this::parsePathFromRoot)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return Set.of();
        }
    }

    @Override
    public Set<FilePathWithDocument> findAllPathsWithDocuments() {
        try (Stream<Path> walk = Files.walk(root)) {
            return walk.filter(path -> !Files.isDirectory(path))
                    .map(this::readDocumentFromPath)
                    .map(doc -> new FilePathWithDocument(doc.getPathToDocumentFromRoot().getFilePath(), doc))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return Set.of();
        }
    }

    @Override
    public boolean isPathCreatable(FilePath path) {
        Path toCreate = Path.of(root.toString(), path.getFilePath());

        return !Files.exists(toCreate);
    }

    @Override
    public boolean hasPathChildren(FilePath path) {

        try (Stream<Path> walk = Files.walk(Path.of(root.toString(), path.getFilePath()))) {
            return walk.count() != 1;
        } catch (NoSuchFileException e){
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public void createFileStructure(FilePath path) throws FileModificationException {
        Path toCreate = Path.of(root.toString(), path.getFilePath());

        try {
            Files.createDirectories(toCreate);
        } catch (IOException e) {
            throw new FileModificationException(e);
        }
    }

    @Override
    public void deleteFileStructure(FilePath path) throws FileModificationException {
        Path toDelete = Path.of(root.toString(), path.getFilePath());

        try {
            Files.delete(toDelete);
        } catch (IOException e) {
            throw new FileModificationException(e);
        }
    }

    @Override
    public void deleteFileStructureWithChildren(FilePath path) throws FileModificationException {
        Path toDelete = Path.of(root.toString(), path.getFilePath());

        try (Stream<Path> walk = Files.walk(toDelete)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new FileModificationException(e);
        }
    }

    @Override
    public Set<FilePathWithDocument> findAllChildrenFromPath(FilePath path) {
        Path walkPath = Path.of(root.toString(), path.getFilePath());

        try(Stream<Path> walk = Files.walk(walkPath)) {
            return walk.filter(p -> !p.equals(walkPath))
                    .filter(p -> p.getParent().equals(walkPath))
                    .map(p -> {
                      if(Files.isDirectory(p)){
                          return new FilePathWithDocument(parsePathFromRoot(p).getFilePath(), null);
                      }
                      return new FilePathWithDocument(parsePathFromRoot(p).getFilePath(), readDocumentFromPath(p));
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return Set.of();
        }
    }

    private DocumentWithoutContent readDocumentFromPath(Path p) {

        DocumentWithoutContent document = new DocumentWithoutContent();
        document.setDocumentName(parseDocumentName(p));
        document.setFileType(parseFileType(p));
        document.setPathToDocumentFromRoot(parsePathFromRoot(p));

        return document;
    }

    private FilePath parsePathFromRoot(Path p) {

        if(!Files.isDirectory(p)){
            p = p.getParent();
        }

        String fromRoot = p.toAbsolutePath().toString().replace(root.toString(), "");

        return new FilePath(fromRoot);
    }


    private String parsePathFromDocument(DocumentWithoutContent p) {

        String fromRoot = p.getPathToDocumentFromRoot().getFilePath();
        fromRoot = fromRoot.substring(0, fromRoot.lastIndexOf("\\") + 1);

        return new FilePath(fromRoot).getFilePath();
    }

    private FileType parseFileType(Path path) {
        String pathString = path.toString();
        String fileName = pathString.substring(pathString.lastIndexOf("\\"));

        String fileType = fileName.split("\\.")[1];

        return new FileTypeUtilImpl().fromString(fileType);
    }

    private String parseDocumentName(Path path) {

        String pathString = path.toString();
        String fileName = pathString.substring(pathString.lastIndexOf("\\"));

        return fileName.split("\\.")[0].substring(1);
    }
}
