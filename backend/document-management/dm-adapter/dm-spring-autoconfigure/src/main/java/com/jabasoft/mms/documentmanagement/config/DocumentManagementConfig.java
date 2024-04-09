package com.jabasoft.mms.documentmanagement.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jabasoft.mms.documentmanagement.adapter.FileDocumentDao;
import com.jabasoft.mms.documentmanagement.adapter.JpaDocumentDao;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepositoryWrapper;

@Configuration
@ComponentScan("com.jabasoft.mms.documentmanagement")
class DocumentManagementConfig {

	@Bean
	DocumentRepository documentRepository(FileDocumentDao fileDocumentDao, JpaDocumentDao jpaDocumentDao){
		return new DocumentRepositoryWrapper(List.of(fileDocumentDao), jpaDocumentDao);
	}

}
