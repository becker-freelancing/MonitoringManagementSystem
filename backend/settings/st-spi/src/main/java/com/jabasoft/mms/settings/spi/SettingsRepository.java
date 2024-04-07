package com.jabasoft.mms.settings.spi;

import com.jabasoft.mms.settings.domain.model.LocalDocumentRootPath;

public interface SettingsRepository {

	public LocalDocumentRootPath getLocalDocumentRootPath();

	public void setLocalDocumentRootPath(LocalDocumentRootPath localDocumentRootPath);

}
