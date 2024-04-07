package com.jabasoft.mms.settings.application;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.settings.api.SettingsPort;
import com.jabasoft.mms.settings.domain.model.LocalDocumentRootPath;
import com.jabasoft.mms.settings.spi.SettingsRepository;

@Component
class SettingsInteractor implements SettingsPort{

	private SettingsRepository settingsRepository;

	@Autowired
	public SettingsInteractor(SettingsRepository settingsRepository) {

		this.settingsRepository = settingsRepository;
	}

	@Override
	public Path getLocalDocumentsRootPath() {

		return settingsRepository.getLocalDocumentRootPath().getLocalDocumentRootPath();
	}

	@Override
	public void setLocalDocumentsRootPath(Path path) {

		LocalDocumentRootPath localDocumentRootPath = new LocalDocumentRootPath(path);
		settingsRepository.setLocalDocumentRootPath(localDocumentRootPath);
	}

}
