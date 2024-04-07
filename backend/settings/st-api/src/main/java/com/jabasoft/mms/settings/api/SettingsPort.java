package com.jabasoft.mms.settings.api;

import java.nio.file.Path;

public interface SettingsPort {

	public Path getLocalDocumentsRootPath();

	public void setLocalDocumentsRootPath(Path path);
}
