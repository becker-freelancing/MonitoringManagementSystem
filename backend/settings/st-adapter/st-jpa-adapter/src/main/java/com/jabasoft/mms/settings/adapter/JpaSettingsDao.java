package com.jabasoft.mms.settings.adapter;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.settings.domain.model.LocalDocumentRootPath;
import com.jabasoft.mms.settings.spi.SettingsRepository;

@Component
class JpaSettingsDao implements SettingsRepository {

	private static final String LOCAL_DOCUMENT_ROOT_PATH_SELECTOR = "LOCAL_DOCUMENT_ROOT_PATH";

	private SpringJpaSettingsRepository settingsRepository;

	@Autowired
	public JpaSettingsDao(SpringJpaSettingsRepository settingsRepository) {

		this.settingsRepository = settingsRepository;
	}

	@Override
	public LocalDocumentRootPath getLocalDocumentRootPath() {

		JpaSettings jpaSettings = settingsRepository.findBySelector(LOCAL_DOCUMENT_ROOT_PATH_SELECTOR);

		return new LocalDocumentRootPath(Path.of(jpaSettings.getValue()));
	}

	@Override
	public void setLocalDocumentRootPath(LocalDocumentRootPath localDocumentRootPath) {

		JpaSettings jpaSettings = new JpaSettings();
		jpaSettings.setSelector(LOCAL_DOCUMENT_ROOT_PATH_SELECTOR);
		jpaSettings.setValue(localDocumentRootPath.getLocalDocumentRootPath().toAbsolutePath().toString());

		settingsRepository.save(jpaSettings);
	}

}
