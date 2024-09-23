package com.jabasoft.mms.documentmanagement.entity;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                "PDF",
                Set.of()
        );

        assertEquals(12L, document.getDocumentId());
        assertEquals("root/test", document.getPathToDocumentFromRoot());
        assertEquals("Test", document.getDocumentName());
        assertEquals("PDF", document.getFileType());
    }
}