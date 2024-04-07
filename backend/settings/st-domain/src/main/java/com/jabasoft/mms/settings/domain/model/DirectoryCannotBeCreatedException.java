package com.jabasoft.mms.settings.domain.model;

import java.nio.file.Path;

class DirectoryCannotBeCreatedException extends RuntimeException{

	private Path directory;

	public DirectoryCannotBeCreatedException(Path directory, Exception e){
		super(directory.toString(), e);
		this.directory = directory;
	}

	public Path getDirectory() {

		return directory;
	}

}
