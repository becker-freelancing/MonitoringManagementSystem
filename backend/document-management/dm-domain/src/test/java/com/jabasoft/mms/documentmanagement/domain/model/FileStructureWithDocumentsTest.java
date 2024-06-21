package com.jabasoft.mms.documentmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

class FileStructureWithDocumentsTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(FileStructureWithDocuments.class);
	}


	@Test
	void testConstructorWithCurrentNameSavesCurrentName(){

		String expected = "test";

		FileStructure fileStructure = new FileStructure(expected);

		assertEquals(expected, fileStructure.getCurrent());
	}

	@Test
	void testAddDocumentAddsDocument(){
		FileStructureWithDocuments structure = new FileStructureWithDocuments();
		assertEquals(0, structure.getDocuments().size());

		structure.addDocument(new DocumentWithoutContent());

		assertEquals(1, structure.getDocuments().size());
	}

	@Test
	void testMappingConstructorMapsFileFileStructure(){
		FileStructure fileStructure = new FileStructure("root");
		fileStructure.addChildren(new FileStructure("test"));
		fileStructure.addChildren(new FileStructure("abc"));

		FileStructureWithDocuments fileStructureWithDocuments = new FileStructureWithDocuments(fileStructure);

		assertEquals("root", fileStructureWithDocuments.getCurrent());
		assertEquals(0, fileStructureWithDocuments.getDocuments().size());
		assertEquals(2, fileStructureWithDocuments.getChildren().size());
		assertEquals(0, fileStructureWithDocuments.getChildren().get(0).getChildren().size());
		assertEquals(0, fileStructureWithDocuments.getChildren().get(0).getDocuments().size());
		assertEquals(0, fileStructureWithDocuments.getChildren().get(1).getChildren().size());
		assertEquals(0, fileStructureWithDocuments.getChildren().get(1).getDocuments().size());
	}
}