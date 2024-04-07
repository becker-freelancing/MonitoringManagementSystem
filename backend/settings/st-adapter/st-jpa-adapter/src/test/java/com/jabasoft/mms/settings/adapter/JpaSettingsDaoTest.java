package com.jabasoft.mms.settings.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.settings.domain.model.LocalDocumentRootPath;

@MmsDaoImplTest
class JpaSettingsDaoTest {

	JpaSettingsDao settingsDao;

	@Autowired
	public JpaSettingsDaoTest(JpaSettingsDao settingsDao) {

		this.settingsDao = settingsDao;
	}

	@Test
	void testSetAndGetLocalDocumentRootPath(@TempDir Path tempDir){

		LocalDocumentRootPath expected = new LocalDocumentRootPath(tempDir);
		settingsDao.setLocalDocumentRootPath(expected);

		LocalDocumentRootPath actual = settingsDao.getLocalDocumentRootPath();

		assertEquals(expected, actual);
	}



}