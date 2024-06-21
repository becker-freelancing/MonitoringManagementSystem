package com.jabasoft.mms.documentmanagement.config;

import java.util.List;

import com.jabasoft.mms.documentmanagement.filepath.adapter.FilePathDao;
import com.jabasoft.mms.documentmanagement.filepath.adapter.JpaFilePathDao;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepositoryWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jabasoft.mms.documentmanagement.adapter.FileDocumentDao;
import com.jabasoft.mms.documentmanagement.adapter.JpaDocumentDao;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepositoryWrapper;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.jabasoft.mms.documentmanagement")
class DocumentManagementConfig {

	@Bean
	DocumentRepository documentRepository(FileDocumentDao fileDocumentDao, JpaDocumentDao jpaDocumentDao){
		return new DocumentRepositoryWrapper(List.of(fileDocumentDao), jpaDocumentDao);
	}

	@Bean
	@Primary
	FilePathRepository filePathRepository(FilePathDao filePathDao, JpaFilePathDao jpaFilePathDao){
		return new FilePathRepositoryWrapper(List.of(filePathDao), jpaFilePathDao);
	}

}
