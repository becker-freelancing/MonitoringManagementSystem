package com.jabasoft.mms.documentmanagement.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaDocumentRepository extends CrudRepository<JpaDocument, String> {

	public JpaDocument findByRelativePath(String relativePath);

}
