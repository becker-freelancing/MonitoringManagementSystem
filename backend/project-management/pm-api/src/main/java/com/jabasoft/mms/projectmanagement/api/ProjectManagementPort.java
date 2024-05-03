package com.jabasoft.mms.projectmanagement.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.projectmanagement.dto.ProjectDto;

public interface ProjectManagementPort {

	public List<ProjectDto> findAllForCustomer(Long customerId);
	public Optional<ProjectDto> saveProject(ProjectDto project);
	public List<ProjectDto> findAll();
	public Optional<ProjectDto> deleteProject(Long projectId);
	public Optional<ProjectDto> getProject(Long projectId);

}
