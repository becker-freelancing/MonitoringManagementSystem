package com.jabasoft.mms.projectmanagement.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.projectmanagement.domain.model.Project;

public interface ProjectRepository {

	public Optional<Project> deleteById(Long projectId);
	public List<Project> findAll();
	public Optional<Project> findById(Long projectId);
	public Optional<Project> save(Project project);

}
