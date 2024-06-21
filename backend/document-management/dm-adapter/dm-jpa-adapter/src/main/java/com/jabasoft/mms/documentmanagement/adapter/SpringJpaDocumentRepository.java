package com.jabasoft.mms.documentmanagement.adapter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jabasoft.mms.documentmanagement.entity.JpaDocument;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaDocumentRepository extends CrudRepository<JpaDocument, Long> {

	public boolean existsJpaDocumentByPathToDocumentFromRootAndDocumentName(String path, String name);

	public Optional<JpaDocument> findByPathToDocumentFromRootAndDocumentName(String path, String name);
}
