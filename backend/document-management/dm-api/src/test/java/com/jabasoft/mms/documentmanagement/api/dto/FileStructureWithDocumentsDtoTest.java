package com.jabasoft.mms.documentmanagement.api.dto;

import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import com.jabasoft.mms.documentmanagement.dto.FileStructureWithDocumentsDto;
import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileStructureWithDocumentsDtoTest extends DynamicBeanTest {

    @Override
    protected Stream<Class<?>> beanClasses() {
        return Stream.of(FileStructureWithDocumentsDto.class);
    }


    @Test
    void testAddChildrenAddsChildren(){

        FileStructureWithDocumentsDto fileStructureDto = new FileStructureWithDocumentsDto();

        fileStructureDto.addChildren(new FileStructureWithDocumentsDto());

        assertEquals(1, fileStructureDto.getChildren().size());
    }


    @Test
    void testAddDocumentAddsDocument(){

        FileStructureWithDocumentsDto fileStructureDto = new FileStructureWithDocumentsDto();

        fileStructureDto.addDocument(new DocumentWithoutContentDto());

        assertEquals(1, fileStructureDto.getDocuments().size());
    }
}