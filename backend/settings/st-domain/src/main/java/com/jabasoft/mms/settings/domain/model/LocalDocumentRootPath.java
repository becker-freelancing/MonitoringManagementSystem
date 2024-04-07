package com.jabasoft.mms.settings.domain.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LocalDocumentRootPath {

	private Path localDocumentRootPath;

	public LocalDocumentRootPath(Path localDocumentRootPath) {

		if(!Files.exists(localDocumentRootPath)){
			try {
				Files.createDirectories(localDocumentRootPath);
			}
			catch (IOException e) {
				throw new DirectoryCannotBeCreatedException(localDocumentRootPath, e);
			}
		}

		this.localDocumentRootPath = localDocumentRootPath;
	}

	public Path getLocalDocumentRootPath() {

		return localDocumentRootPath;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LocalDocumentRootPath that = (LocalDocumentRootPath) o;
		return Objects.equals(localDocumentRootPath, that.localDocumentRootPath);
	}

	@Override
	public int hashCode() {

		return Objects.hash(localDocumentRootPath);
	}

}
