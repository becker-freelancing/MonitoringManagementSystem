package com.jabasoft.mms.documentmanagement.domain.service.filestructure;

import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FilePathDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileStructureServiceImplTest {

    FileStructureServiceImpl fileStructureService;

    @BeforeEach
    void setUp(){
        fileStructureService = new FileStructureServiceImpl();
    }

    @Test
    void testAddDocumentThrowsExceptionWhenFilePathDoesNotExist(){

        FileStructureWithDocuments fileStructure = new FileStructureWithDocuments(new FileStructure("root"));
        FilePathWithDocument filePathWithDocument = new FilePathWithDocument("root/notexisting", new DocumentWithoutContent());

        assertThrows(FilePathDoesNotExistException.class, () -> fileStructureService.addDocument(fileStructure, filePathWithDocument));
    }

    @Test
    void testAddDocumentAddsDocumentIfPathExists(){
        FileStructure fileStructure = new FileStructure("root");
        FileStructure child = new FileStructure("child");
        FileStructure test = new FileStructure("Test");
        child.addChildren(test);
        fileStructure.addChildren(child);

        DocumentWithoutContent documentWithoutContent = new DocumentWithoutContent();
        FileStructureWithDocuments fileStructureWithDocuments = new FileStructureWithDocuments(fileStructure);

        FileStructureWithDocuments actual = fileStructureService.addDocument(fileStructureWithDocuments, new FilePathWithDocument("root/child/Test", documentWithoutContent));

        assertEquals(1, actual.getChildren().size());
        assertEquals(0, actual.getDocuments().size());

        FileStructureWithDocuments actualChild = actual.getChildren().get(0);
        assertEquals(1, actualChild.getChildren().size());
        assertEquals(0, actualChild.getDocuments().size());


        FileStructureWithDocuments actualTestChild = actualChild.getChildren().get(0);
        assertEquals(0, actualTestChild.getChildren().size());
        assertEquals(1, actualTestChild.getDocuments().size());
    }

    @Test
    void testAddFilePathAddsDocumentIfPathExists(){
        FileStructure fileStructure = new FileStructure("root");
        fileStructure.addChildren(new FileStructure("child"));

        FileStructure actual = fileStructureService.addFilePath(fileStructure, new FilePath("root/child/test"));

        assertEquals(1, actual.getChildren().size());
        assertEquals("root", actual.getCurrent());

        FileStructure actualChild = actual.getChildren().get(0);
        assertEquals(1, actualChild.getChildren().size());
        assertEquals("child", actualChild.getCurrent());


        FileStructure actualTestChild = actualChild.getChildren().get(0);
        assertEquals(0, actualTestChild.getChildren().size());
        assertEquals("test", actualTestChild.getCurrent());
    }

}