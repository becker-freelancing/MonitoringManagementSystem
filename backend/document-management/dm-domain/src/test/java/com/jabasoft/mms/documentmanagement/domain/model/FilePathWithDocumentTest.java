package com.jabasoft.mms.documentmanagement.domain.model;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilePathWithDocumentTest extends DynamicBeanTest {

    @Override
    protected Stream<Class<?>> beanClasses() {
        return Stream.of(FilePathWithDocument.class);
    }

    @Test
    void testConstructorWithNameAndDocumentSavesNameAndDocument(){
        DocumentWithoutContent document = new DocumentWithoutContent();
        document.setDocumentName("Test");

        FilePathWithDocument filePathWithDocument = new FilePathWithDocument("root/a/b", document);

        assertEquals("root\\a\\b", filePathWithDocument.getFilePath());
        assertEquals("Test", filePathWithDocument.getDocument().getDocumentName());
    }
}