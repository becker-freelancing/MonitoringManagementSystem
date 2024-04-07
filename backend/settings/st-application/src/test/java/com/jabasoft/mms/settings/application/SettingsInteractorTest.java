package com.jabasoft.mms.settings.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import com.jabasoft.mms.settings.domain.model.LocalDocumentRootPath;
import com.jabasoft.mms.settings.spi.SettingsRepository;

class SettingsInteractorTest {

	SettingsInteractor interactor;
	SettingsRepository settingsRepository;

	@BeforeEach
	void setUp(){

		settingsRepository = mock(SettingsRepository.class);

		interactor = new SettingsInteractor(settingsRepository);
	}

	@Test
	void testGetLocalDocumentRootPath(@TempDir Path tempDir){

		LocalDocumentRootPath expected = new LocalDocumentRootPath(tempDir);
		Mockito.when(settingsRepository.getLocalDocumentRootPath()).thenReturn(expected);

		Path actual = interactor.getLocalDocumentsRootPath();

		assertEquals(tempDir, actual);
	}


}