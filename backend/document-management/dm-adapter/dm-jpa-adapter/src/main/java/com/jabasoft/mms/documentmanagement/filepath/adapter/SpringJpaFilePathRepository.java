package com.jabasoft.mms.documentmanagement.filepath.adapter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jabasoft.mms.documentmanagement.entity.JpaDocumentWithoutContent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jabasoft.mms.documentmanagement.entity.JpaDocument;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaFilePathRepository extends CrudRepository<JpaDocument, Long> {

	@Query("select distinct doc.pathToDocumentFromRoot from JpaDocument doc")
	public Set<String> findAllPathsFromDocumentRootPath();

	@Query("select new com.jabasoft.mms.documentmanagement.entity.JpaDocumentWithoutContent(doc.documentId, doc.pathToDocumentFromRoot, doc.documentName, doc.fileType) from JpaDocument doc where doc.documentName is not null")
	public Set<JpaDocumentWithoutContent> findAllPathsWithDocuments();

	public boolean existsByPathToDocumentFromRoot(String path);

	@Query("select new com.jabasoft.mms.documentmanagement.entity.JpaDocumentWithoutContent(doc.documentId, doc.pathToDocumentFromRoot, doc.documentName, doc.fileType) from JpaDocument doc where doc.pathToDocumentFromRoot like ?1")
	public Set<JpaDocumentWithoutContent> findPathLike(String like);

	public void deleteByPathToDocumentFromRoot(String path);


	public void deleteAllByPathToDocumentFromRootStartsWith(String path);

	public void deleteAllByPathToDocumentFromRoot(String path);
}
