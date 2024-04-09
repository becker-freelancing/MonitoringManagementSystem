package com.jabasoft.mms.documentmanagement.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import com.jabasoft.mms.settings.api.SettingsPort;

@Component
public class FileDocumentDao implements DocumentRepository {

	private Path documentRootPath;

	@Autowired
	public FileDocumentDao(SettingsPort settingsPort) {

		this.documentRootPath = settingsPort.getLocalDocumentsRootPath();
	}

	@Override
	public List<Document> getAllDocuments() {

		try (Stream<Path> walk = Files.walk(documentRootPath)) {
			return walk.filter(p -> !Files.isDirectory(p)).map(p -> {
					byte[] content;
					try {
						content = Files.readAllBytes(p);
					}
					catch (IOException e) {
						return null;
					}

					String relativeDocumentPath = p.toString().replace(documentRootPath.toString(), "");
					return new Document(relativeDocumentPath, content, null);
				}
			).toList();
		}
		catch (IOException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Document getDocument(DocumentId documentId) {

		return null;
	}

	@Override
	public DocumentId saveDocument(Document document) {

		try {
			Path writePath = Path.of(documentRootPath.toString(), document.getRelativePath());
			Path directory = writePath;
			if(!Files.isDirectory(writePath)){
				directory = writePath.getParent();
			}
			if(Files.exists(writePath)){
				Files.delete(writePath);
			}
			Files.createDirectories(directory);
			Files.write(writePath, document.getContent());
		}
		catch (IOException ignored) {
		}

		return null;
	}

}
