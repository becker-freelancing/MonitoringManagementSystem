package com.jabasoft.mms.projectmanagement.adapter;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jabasoft.mms.projectmanagement.api.ProjectManagementPort;
import com.jabasoft.mms.projectmanagement.dto.ProjectDto;

@RestController
@RequestMapping("/mms/api/projects")
class ProjectManagementRestAdapter {


	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagementRestAdapter.class);

	private ProjectManagementPort projectManagementPort;

	@Autowired
	public ProjectManagementRestAdapter(ProjectManagementPort projectManagementPort) {

		this.projectManagementPort = projectManagementPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto project){

		try {
			Optional<ProjectDto> projectDto = projectManagementPort.saveProject(project);

			return projectDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in saveProject-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ProjectDto> deleteProject(@PathVariable("id") Long id){
		try {
			Optional<ProjectDto> deleted = projectManagementPort.deleteProject(id);

			return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());

		} catch (Exception e){
			LOGGER.error("Exception in deleteProject-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<ProjectDto> getProject(@PathVariable("id") Long id){

		try{
			Optional<ProjectDto> project = projectManagementPort.getProject(id);

			return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in getProject-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<ProjectDto>> findAll(){
		try{
			List<ProjectDto> all = projectManagementPort.findAll();

			return ResponseEntity.ok(all);
		} catch (Exception e){
			LOGGER.error("Exception in findAllProjects-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/customer/get/{id}")
	public ResponseEntity<List<ProjectDto>> findAllForCustomer(@PathVariable Long id){
		try{
			List<ProjectDto> all = projectManagementPort.findAllForCustomer(id);

			return ResponseEntity.ok(all);
		} catch (Exception e){
			LOGGER.error("Exception in findAllProjects-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
