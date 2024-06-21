package com.jabasoft.mms.documentmanagement.api.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.documentmanagement.dto.FileStructureDto;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class FileStructureDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(FileStructureDto.class);
	}

	@Test
	void testAddChildrenAddsChildren(){

		FileStructureDto fileStructureDto = new FileStructureDto();

		fileStructureDto.addChildren(new FileStructureDto());

		assertEquals(1, fileStructureDto.getChildren().size());
	}
}