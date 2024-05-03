package com.jabasoft.mms.projectmanagement.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.projectmanagement.domain.model.Project;
import com.jabasoft.mms.projectmanagement.spi.ProjectRepository;

@Component
class JpaProjectDao implements ProjectRepository {

	private SpringJpaProjectRepository projectRepository;

	@Autowired
	public JpaProjectDao(SpringJpaProjectRepository projectRepository) {

		this.projectRepository = projectRepository;
	}

	@Override
	public Optional<Project> deleteById(Long projectId) {

		if(!projectRepository.existsById(projectId)){
			return Optional.empty();
		}

		Optional<JpaProject> deleted = projectRepository.findById(projectId);

		projectRepository.deleteById(projectId);

		return deleted.map(this::mapToDomain);
	}

	@Override
	public List<Project> findAll() {

		List<JpaProject> all = new ArrayList<>();

		projectRepository.findAll().forEach(all::add);

		return all.stream().map(this::mapToDomain).toList();
	}

	@Override
	public List<Project> findAllForCustomer(Long customerId) {

		List<JpaProject> all = projectRepository.findAllByCustomerId(customerId);

		return all.stream().map(this::mapToDomain).toList();
	}

	@Override
	public Optional<Project> findById(Long projectId) {

		Optional<JpaProject> find = projectRepository.findById(projectId);

		return find.map(this::mapToDomain);
	}

	@Override
	public Optional<Project> save(Project project) {

		JpaProject entity = this.mapToEntity(project);

		JpaProject saved = projectRepository.save(entity);

		return Optional.of(saved).map(this::mapToDomain);
	}

	private JpaProject mapToEntity(Project project){

		JpaProject jpaProject = new JpaProject();

		jpaProject.setProjectId(project.getProjectId());
		jpaProject.setTitle(project.getTitle());
		jpaProject.setShortDescription(project.getShortDescription());
		jpaProject.setLongDescription(project.getLongDescription());
		jpaProject.setCreationTime(project.getCreationTime());
		jpaProject.setStartTime(project.getStartTime());
		jpaProject.setEndTime(project.getEndTime());
		jpaProject.setClosedTime(project.getClosedTime());
		jpaProject.setCustomerId(project.getCustomerId());

		return jpaProject;
	}

	private Project mapToDomain(JpaProject jpaProject){

		Project project = new Project();

		project.setProjectId(jpaProject.getProjectId());
		project.setTitle(jpaProject.getTitle());
		project.setShortDescription(jpaProject.getShortDescription());
		project.setLongDescription(jpaProject.getLongDescription());
		project.setCreationTime(jpaProject.getCreationTime());
		project.setStartTime(jpaProject.getStartTime());
		project.setEndTime(jpaProject.getEndTime());
		project.setClosedTime(jpaProject.getClosedTime());
		project.setCustomerId(jpaProject.getCustomerId());

		return project;
	}
}
