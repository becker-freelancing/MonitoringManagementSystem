package com.jabasoft.mms.documentmanagement.domain.model;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileStructureTest extends DynamicBeanTest {

    @Override
    protected Stream<Class<?>> beanClasses() {
        return Stream.of(FileStructure.class);
    }

    @Test
    void testConstructorWithCurrentNameSavesCurrentName(){

        String expected = "test";

        FileStructure fileStructure = new FileStructure(expected);

        assertEquals(expected, fileStructure.getCurrent());
    }

    @Test
    void testAddChildrenAddsChildren(){
        FileStructure structure = new FileStructure();
        assertEquals(0, structure.getChildren().size());

        structure.addChildren(new FileStructure());

        assertEquals(1, structure.getChildren().size());
    }
}