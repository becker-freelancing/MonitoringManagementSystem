package com.jabasoft.mms.projectmanagement.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.projectmanagement.api.ProjectManagementPort;
import com.jabasoft.mms.projectmanagement.domain.model.Project;
import com.jabasoft.mms.projectmanagement.dto.ProjectDto;
import com.jabasoft.mms.projectmanagement.spi.ProjectRepository;

@Component
class ProjectManagementInteractor implements ProjectManagementPort {

	private ProjectRepository projectRepository;

	@Autowired
	public ProjectManagementInteractor(ProjectRepository projectRepository) {

		this.projectRepository = projectRepository;
	}

	@Override
	public List<ProjectDto> findAllForCustomer(Long customerId) {

		List<Project> allProjects = projectRepository.findAllForCustomer(customerId);

		return allProjects.stream().map(this::mapToDto).toList();
	}

	@Override
	public Optional<ProjectDto> saveProject(ProjectDto dto) {

		Project project = mapToDomain(dto);

		Optional<Project> saved = projectRepository.save(project);

		return saved.map(this::mapToDto);
	}

	@Override
	public List<ProjectDto> findAll() {

		List<Project> allProjects = projectRepository.findAll();

		return allProjects.stream().map(this::mapToDto).toList();
	}

	@Override
	public Optional<ProjectDto> deleteProject(Long projectId) {

		Optional<Project> deleted = projectRepository.deleteById(projectId);

		return deleted.map(this::mapToDto);
	}

	@Override
	public Optional<ProjectDto> getProject(Long projectId) {

		Optional<Project> find = projectRepository.findById(projectId);

		return find.map(this::mapToDto);
	}

	private Project mapToDomain(ProjectDto projectDto){

		Project project = new Project();

		project.setProjectId(projectDto.getProjectId());
		project.setTitle(projectDto.getTitle());
		project.setShortDescription(projectDto.getShortDescription());
		project.setLongDescription(projectDto.getLongDescription());
		project.setCreationTime(projectDto.getCreationTime());
		project.setStartTime(projectDto.getStartTime());
		project.setEndTime(projectDto.getEndTime());
		project.setClosedTime(project.getClosedTime());
		project.setCustomerId(projectDto.getCustomerId());

		return project;
	}

	private ProjectDto mapToDto(Project project){

		ProjectDto projectDto = new ProjectDto();

		projectDto.setProjectId(project.getProjectId());
		projectDto.setTitle(project.getTitle());
		projectDto.setShortDescription(project.getShortDescription());
		projectDto.setLongDescription(project.getLongDescription());
		projectDto.setCreationTime(project.getCreationTime());
		projectDto.setStartTime(project.getStartTime());
		projectDto.setEndTime(project.getEndTime());
		projectDto.setClosedTime(project.getClosedTime());
		projectDto.setCustomerId(project.getCustomerId());

		return projectDto;
	}

}
