package com.jabasoft.mms.projectmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.projectmanagement.MmsDaoImplTest;
import com.jabasoft.mms.projectmanagement.domain.model.Project;

@MmsDaoImplTest
class JpaProjectDaoTest {

	private JpaProjectDao dao;

	@Autowired
	public JpaProjectDaoTest(JpaProjectDao dao) {

		this.dao = dao;
	}

	@Test
	void testSaveProjectReturnsProjectOnSuccess() {

		Project expected = createProjectAsDomain();

		Optional<Project> saved = dao.save(expected);

		assertTrue(saved.isPresent());

		Project actual = saved.get();

		assertNotNull(actual.getProjectId(), "ProjectId");
		assertEquals("Titel", actual.getTitle(), "Title");
		assertEquals("Kurzbeschreibung", actual.getShortDescription(), "ShortDescription");
		assertEquals("Lange Beschreibung", actual.getLongDescription(), "LongDescription");
		assertEquals(LocalDateTime.parse("-999999999-01-01T00:00"), actual.getCreationTime(), "CreationTime");
		assertEquals(LocalDateTime.parse("2024-04-29T12:00"), actual.getStartTime(), "StartTime");
		assertEquals(LocalDateTime.parse("+999999999-12-31T23:59:59.999999999"), actual.getEndTime(), "EndTime");
		assertEquals(18, actual.getCustomerId(), "CustomerId");
	}

	@Test
	@SqlInsertProjectData
	void testFindAllReturnsListWithMultipleProjects() {

		List<Project> find = dao.findAll();

		assertEquals(2, find.size());

		Project actual1 = find.get(0);

		assertNotNull(actual1.getProjectId(), "ProjectId");
		assertEquals("Titel", actual1.getTitle(), "Title");
		assertEquals("Kurzbeschreibung", actual1.getShortDescription(), "ShortDescription");
		assertEquals("Langbeschreibung", actual1.getLongDescription(), "LongDescription");
		assertEquals(LocalDateTime.parse("2024-04-29T12:00:00"), actual1.getCreationTime(), "CreationTime");
		assertEquals(LocalDateTime.parse("2024-04-30T00:00:00"), actual1.getStartTime(), "StartTime");
		assertEquals(LocalDateTime.parse("2025-04-29T00:00:00"), actual1.getEndTime(), "EndTime");
		assertEquals(12, actual1.getCustomerId(), "CustomerId");

		Project actual2 = find.get(1);

		assertNotNull(actual2.getProjectId(), "ProjectId");
		assertEquals("Titel 2", actual2.getTitle(), "Title");
		assertEquals("Kurzbeschreibung 2", actual2.getShortDescription(), "ShortDescription");
		assertEquals("Langbeschreibung 2", actual2.getLongDescription(), "LongDescription");
		assertEquals(LocalDateTime.parse("2024-05-29T12:00:00"), actual2.getCreationTime(), "CreationTime");
		assertEquals(LocalDateTime.parse("2024-07-30T00:00:00"), actual2.getStartTime(), "StartTime");
		assertEquals(LocalDateTime.parse("2024-08-29T00:00:00"), actual2.getEndTime(), "EndTime");
		assertEquals(4, actual2.getCustomerId(), "CustomerId");
	}

	@Test
	void testFindAllReturnsEmptyListWhenNoProjectExists() {

		List<Project> find = dao.findAll();

		assertEquals(0, find.size());
	}

	@Test
	@SqlInsertProjectData
	void testDeleteProjectDeletesProjectWhenProjectExists() {

		List<Project> all = dao.findAll();

		assertFalse(all.isEmpty());

		Project expected = all.get(0);

		Optional<Project> deleted = dao.deleteById(expected.getProjectId());

		assertTrue(deleted.isPresent());

		Project actual = deleted.get();

		assertEquals(expected.getProjectId(), actual.getProjectId(), "ProjectId");
		assertEquals(expected.getTitle(), actual.getTitle(), "Title");
		assertEquals(expected.getShortDescription(), actual.getShortDescription(), "ShortDescription");
		assertEquals(expected.getLongDescription(), actual.getLongDescription(), "LongDescription");
		assertEquals(expected.getCreationTime(), actual.getCreationTime(), "CreationTime");
		assertEquals(expected.getStartTime(), actual.getStartTime(), "StartTime");
		assertEquals(expected.getEndTime(), actual.getEndTime(), "EndTime");
		assertEquals(expected.getCustomerId(), actual.getCustomerId(), "CustomerId");
	}

	@Test
	void testDeleteProjectReturnsEmptyOptionalWhenNoProjectExists() {

		Optional<Project> deleted = dao.deleteById(12L);

		assertTrue(deleted.isEmpty());
	}

	@Test
	@SqlInsertProjectData
	void testGetProjectReturnsProjectWhenProjectExists() {

		Optional<Project> find = dao.findById(1L);

		assertTrue(find.isPresent());

		Project actual = find.get();

		assertEquals(1, actual.getProjectId(), "ProjectId");
		assertEquals("Titel", actual.getTitle(), "Title");
		assertEquals("Kurzbeschreibung", actual.getShortDescription(), "ShortDescription");
		assertEquals("Langbeschreibung", actual.getLongDescription(), "LongDescription");
		assertEquals(LocalDateTime.parse("2024-04-29T12:00:00"), actual.getCreationTime(), "CreationTime");
		assertEquals(LocalDateTime.parse("2024-04-30T00:00:00"), actual.getStartTime(), "StartTime");
		assertEquals(LocalDateTime.parse("2025-04-29T00:00:00"), actual.getEndTime(), "EndTime");
		assertEquals(12, actual.getCustomerId(), "CustomerId");
	}

	@Test
	void testGetProjectReturnsEmptyOptionalWhenNoProjectExists() {

		Optional<Project> find = dao.findById(12L);

		assertTrue(find.isEmpty());
	}

	private Project createProjectAsDomain() {

		Project project = new Project();

		project.setTitle("Titel");
		project.setShortDescription("Kurzbeschreibung");
		project.setLongDescription("Lange Beschreibung");
		project.setCreationTime(LocalDateTime.MIN);
		project.setStartTime(LocalDateTime.of(2024, 4, 29, 12, 0, 0));
		project.setEndTime(LocalDateTime.MAX);
		project.setCustomerId(18L);

		return project;
	}
}