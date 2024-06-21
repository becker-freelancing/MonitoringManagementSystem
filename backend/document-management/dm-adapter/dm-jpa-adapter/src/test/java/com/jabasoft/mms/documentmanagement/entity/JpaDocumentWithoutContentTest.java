package com.jabasoft.mms.documentmanagement.entity;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JpaDocumentWithoutContentTest extends DynamicBeanTest {

    @Override
    protected Stream<Class<?>> beanClasses() {
        return Stream.of(JpaDocumentWithoutContent.class);
    }

    @Test
    void testConstructorSetsAttributesCorrectly(){
        JpaDocumentWithoutContent document = new JpaDocumentWithoutContent(
                12L,
                "root/test",
                "Test",
                2L
        );

        assertEquals(12L, document.getDocumentId());
        assertEquals("root/test", document.getPathToDocumentFromRoot());
        assertEquals("Test", document.getDocumentName());
        assertEquals(2L, document.getFileType());
    }
}