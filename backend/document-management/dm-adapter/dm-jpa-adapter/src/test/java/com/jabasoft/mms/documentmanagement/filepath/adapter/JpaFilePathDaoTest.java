package com.jabasoft.mms.documentmanagement.filepath.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;

import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.documentmanagement.MmsDaoImplTest;
import com.jabasoft.mms.documentmanagement.adapter.JpaDocumentDao;

@MmsDaoImplTest
class JpaFilePathDaoTest {

	JpaFilePathDao filePathDao;
	JpaDocumentDao documentDao;

	@Autowired
	public JpaFilePathDaoTest(JpaFilePathDao filePathDao, JpaDocumentDao documentDao) {

		this.filePathDao = filePathDao;
		this.documentDao = documentDao;
	}

	@Test
	void testFindAllPathsReturnsEmptySetWhenNoPathsExists(){

		Set<FilePath> allPaths = filePathDao.findAllPaths();

		assertEquals(0, allPaths.size());
	}

	@Test
	void testFindAllPathsReturnsSetWithAllPathsWhenDifferentPathsExists(){
		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b", "abababa", FileType.PDF));

		Set<FilePath> allPaths = filePathDao.findAllPaths();

		assertEquals(3, allPaths.size());
		assertEquals(Set.of(new FilePath(""), new FilePath("test"), new FilePath("a\\b")), allPaths);
	}

	Document getDocument(String relativePath, String documentName, FileType fileType){

		Document document = new Document();

		document.setDocumentName(documentName);
		document.setFileType(fileType);
		document.setContent(new byte[]{12, 34, 54, 32});
		document.setPathToDocumentFromRoot(new FilePath(relativePath));

		return document;
	}

	@Test
	void testFindAllPathsWithDocumentsReturnsEmptySetIfNoDocumentsExist(){
		Set<FilePathWithDocument> pathsWithDocuments = filePathDao.findAllPathsWithDocuments();

		assertEquals(0, pathsWithDocuments.size());
	}

	@Test
	void testFindAllPathsWithDocumentsReturnsAllPaths(){
		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b", "abababa", FileType.PDF));

		Set<FilePathWithDocument> allPaths = filePathDao.findAllPathsWithDocuments();

		assertEquals(4, allPaths.size());

		Set<String> paths = allPaths.stream().map(FilePath::getFilePath).collect(Collectors.toSet());
		assertEquals(Set.of("", "test", "a\\b"), paths);

		Set<String> documentNames = allPaths.stream().map(FilePathWithDocument::getDocument).map(DocumentWithoutContent::getDocumentName).collect(Collectors.toSet());
		assertEquals(Set.of("test", "test45", "abababa"), documentNames);
	}

	@Test
	void testIsPathCreatableReturnsTrueIfPathDoesNotExist(){

		documentDao.saveDocument(getDocument("root/test/", "Test", FileType.PDF));

		boolean pathCreatable = filePathDao.isPathCreatable(new FilePath("root/test/test"));

		assertTrue(pathCreatable);
	}

	@Test
	void testIsPathCreatableReturnsFalseIfPathDoesExist(){

		documentDao.saveDocument(getDocument("root/test/", "Test", FileType.PDF));

		boolean pathCreatable = filePathDao.isPathCreatable(new FilePath("root/test"));

		assertFalse(pathCreatable);
	}

	@Test
	void testHasPathChildrenReturnsTrueIfDocumentsAreInPath(){
		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));

		boolean hasPathChildren = filePathDao.hasPathChildren(new FilePath("test"));

		assertTrue(hasPathChildren);
	}

	@Test
	void testHasPathChildrenReturnsTrueIfPathsAreInPath() throws FileModificationException {

		filePathDao.createFileStructure(new FilePath("root/test/a"));

		boolean hasPathChildren = filePathDao.hasPathChildren(new FilePath("root/test"));

		assertTrue(hasPathChildren);
	}

	@Test
	void testHasPathChildrenReturnsFalseIfPathDoesNotExist(){
		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));

		boolean hasPathChildren = filePathDao.hasPathChildren(new FilePath("test/a"));

		assertFalse(hasPathChildren);
	}

	@Test
	void testHasPathChildrenReturnsFalseIfPathIsEmpty() throws FileModificationException {
		filePathDao.createFileStructure(new FilePath("root/test/a"));

		boolean hasPathChildren = filePathDao.hasPathChildren(new FilePath("root/test/a"));

		assertFalse(hasPathChildren);
	}

	@Test
	void testCreateFileStructureCreatesFileStructure() throws FileModificationException {
		filePathDao.createFileStructure(new FilePath("root/test/a"));

		Set<String> allPaths = filePathDao.findAllPaths().stream()
				.map(FilePath::getFilePath)
				.collect(Collectors.toSet());

		assertEquals(Set.of("root\\test\\a"), allPaths);
	}

	@Test
	void testDeleteFileStructureThrowsExceptionWhenFileStructureHasChildren(){
		documentDao.saveDocument(getDocument("root/test/a", "Test", FileType.PDF));

		assertThrows(FileModificationException.class, () -> filePathDao.deleteFileStructure(new FilePath("root/test")));
	}

	@Test
	void testDeleteFileStructureDeletesFileStructureWhenFileStructureHasNoChildren() throws FileModificationException {

		filePathDao.createFileStructure(new FilePath("root/test/a"));
		filePathDao.createFileStructure(new FilePath("root/test"));
		filePathDao.createFileStructure(new FilePath("root/test2"));

		filePathDao.deleteFileStructure(new FilePath("root/test2"));

		Set<String> filePaths = filePathDao.findAllPaths().stream()
				.map(FilePath::getFilePath)
				.collect(Collectors.toSet());

		assertEquals(Set.of("root\\test\\a", "root\\test"), filePaths);
	}

	@Test
	void testDeleteFileStructureWithChildrenDeletesFileStructureWithChildren() throws FileModificationException {
		filePathDao.createFileStructure(new FilePath("root/test/a"));
		filePathDao.createFileStructure(new FilePath("root/test"));
		filePathDao.createFileStructure(new FilePath("root/test2"));
		documentDao.saveDocument(getDocument("root/test", "Test", FileType.PDF));

		filePathDao.deleteFileStructureWithChildren(new FilePath("root/test"));

		Set<String> filePaths = filePathDao.findAllPaths().stream()
				.map(FilePath::getFilePath)
				.collect(Collectors.toSet());

		assertEquals(Set.of("root\\test2"), filePaths);
	}
}