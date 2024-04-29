package com.jabasoft.mms.projectmanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.projectmanagement.domain.model.Project;
import com.jabasoft.mms.projectmanagement.dto.ProjectDto;
import com.jabasoft.mms.projectmanagement.spi.ProjectRepository;

class ProjectManagementInteractorTest {

	private ProjectRepository projectRepository;
	private ProjectManagementInteractor interactor;

	@BeforeEach
	void setUp() {

		projectRepository = mock(ProjectRepository.class);
		interactor = new ProjectManagementInteractor(projectRepository);
	}

	@Test
	void testSaveProjectReturnsProjectOnSuccess() {

		Project expected = createProjectAsDomain();

		when(projectRepository.save(any())).thenReturn(Optional.of(expected));

		Optional<ProjectDto> saved = interactor.saveProject(createProjectAsDto());

		assertTrue(saved.isPresent());

		ProjectDto actual = saved.get();

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
	void testSaveProjectReturnsEmptyOptionalOnFail() {

		when(projectRepository.save(any())).thenReturn(Optional.empty());

		Optional<ProjectDto> saved = interactor.saveProject(createProjectAsDto());

		assertTrue(saved.isEmpty());
	}

	@Test
	void testFindAllReturnsListWithMultipleProjects() {

		Project expected1 = createProjectAsDomain();
		Project expected2 = createProjectAsDomain();
		expected2.setTitle("Titel 2");

		when(projectRepository.findAll()).thenReturn(List.of(expected1, expected2));

		List<ProjectDto> find = interactor.findAll();

		assertEquals(2, find.size());

		ProjectDto actual1 = find.get(0);

		assertEquals(expected1.getProjectId(), actual1.getProjectId(), "ProjectId");
		assertEquals(expected1.getTitle(), actual1.getTitle(), "Title");
		assertEquals(expected1.getShortDescription(), actual1.getShortDescription(), "ShortDescription");
		assertEquals(expected1.getLongDescription(), actual1.getLongDescription(), "LongDescription");
		assertEquals(expected1.getCreationTime(), actual1.getCreationTime(), "CreationTime");
		assertEquals(expected1.getStartTime(), actual1.getStartTime(), "StartTime");
		assertEquals(expected1.getEndTime(), actual1.getEndTime(), "EndTime");
		assertEquals(expected1.getCustomerId(), actual1.getCustomerId(), "CustomerId");

		ProjectDto actual2 = find.get(1);

		assertEquals(expected2.getProjectId(), actual2.getProjectId(), "ProjectId");
		assertEquals(expected2.getTitle(), actual2.getTitle(), "Title");
		assertEquals(expected2.getShortDescription(), actual2.getShortDescription(), "ShortDescription");
		assertEquals(expected2.getLongDescription(), actual2.getLongDescription(), "LongDescription");
		assertEquals(expected2.getCreationTime(), actual2.getCreationTime(), "CreationTime");
		assertEquals(expected2.getStartTime(), actual2.getStartTime(), "StartTime");
		assertEquals(expected2.getEndTime(), actual2.getEndTime(), "EndTime");
		assertEquals(expected2.getCustomerId(), actual2.getCustomerId(), "CustomerId");
	}

	@Test
	void testFindAllReturnsEmptyListWhenNoProjectExists() {
		when(projectRepository.findAll()).thenReturn(List.of());

		List<ProjectDto> find = interactor.findAll();

		assertEquals(0, find.size());
	}

	@Test
	void testDeleteProjectDeletesProjectWhenProjectExists() {
		Project expected = createProjectAsDomain();

		when(projectRepository.deleteById(any())).thenReturn(Optional.of(expected));

		Optional<ProjectDto> deleted = interactor.deleteProject(expected.getProjectId());

		assertTrue(deleted.isPresent());

		ProjectDto actual = deleted.get();

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
		when(projectRepository.deleteById(any())).thenReturn(Optional.empty());

		Optional<ProjectDto> deleted = interactor.deleteProject(12L);

		assertTrue(deleted.isEmpty());
	}

	@Test
	void testGetProjectReturnsProjectWhenProjectExists() {
		Project expected = createProjectAsDomain();

		when(projectRepository.findById(any())).thenReturn(Optional.of(expected));

		Optional<ProjectDto> find = interactor.getProject(expected.getProjectId());

		assertTrue(find.isPresent());

		ProjectDto actual = find.get();

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
	void testGetProjectReturnsEmptyOptionalWhenNoProjectExists() {
		when(projectRepository.findById(any())).thenReturn(Optional.empty());

		Optional<ProjectDto> find = interactor.getProject(12L);

		assertTrue(find.isEmpty());
	}

	private Project createProjectAsDomain() {

		Project project = new Project();

		project.setProjectId(567L);
		project.setTitle("Titel");
		project.setShortDescription("Kurzbeschreibung");
		project.setLongDescription("Lange Beschreibung");
		project.setCreationTime(LocalDateTime.MIN);
		project.setStartTime(LocalDateTime.of(2024, 4, 29, 12, 0, 0));
		project.setEndTime(LocalDateTime.MAX);
		project.setCustomerId(18L);

		return project;
	}

	private ProjectDto createProjectAsDto() {

		ProjectDto project = new ProjectDto();

		project.setProjectId(567L);
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