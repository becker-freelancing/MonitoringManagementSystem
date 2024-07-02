package com.jabasoft.mms.documentmanagement.dto;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilePathWithDocumentDtoTest extends DynamicBeanTest {

    @Override
    protected Stream<Class<?>> beanClasses() {
        return Stream.of(FilePathWithDocumentDto.class);
    }
}