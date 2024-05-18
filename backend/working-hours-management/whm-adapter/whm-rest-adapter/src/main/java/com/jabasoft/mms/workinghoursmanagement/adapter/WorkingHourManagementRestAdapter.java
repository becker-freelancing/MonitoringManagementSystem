package com.jabasoft.mms.workinghoursmanagement.adapter;

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

import com.jabasoft.mms.workinghoursmanagement.api.WorkingHourManagementPort;
import com.jabasoft.mms.workinghoursmanagement.dto.WorkingHourDto;

@RestController
@RequestMapping("mms/api/workinghours")
class WorkingHourManagementRestAdapter {


	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingHourManagementRestAdapter.class);


	private WorkingHourManagementPort workingHourManagementPort;

	@Autowired
	public WorkingHourManagementRestAdapter(WorkingHourManagementPort workingHourManagementPort) {

		this.workingHourManagementPort = workingHourManagementPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<WorkingHourDto> saveWorkingHour(@RequestBody WorkingHourDto workingHour){

		try {
			Optional<WorkingHourDto> workingHourDto = workingHourManagementPort.save(workingHour);

			return workingHourDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in saveWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<WorkingHourDto> deleteWorkingHour(@PathVariable("id") Long id){
		try {
			Optional<WorkingHourDto> deleted = workingHourManagementPort.delete(id);

			return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());

		} catch (Exception e){
			LOGGER.error("Exception in deleteWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<WorkingHourDto> getWorkingHour(@PathVariable("id") Long id){

		try{
			Optional<WorkingHourDto> workingHour = workingHourManagementPort.find(id);

			return workingHour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in getWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<WorkingHourDto>> findAll(){
		try{
			List<WorkingHourDto> all = workingHourManagementPort.findAll();

			return ResponseEntity.ok(all);
		} catch (Exception e){
			LOGGER.error("Exception in findAllWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}



	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/open/customer/{id}")
	public ResponseEntity<WorkingHourDto> findCurrentOpenForCustomer(@PathVariable("id") Long customerId){
		try{
			Optional<WorkingHourDto> workingHour = workingHourManagementPort.findCurrentOpenForCustomer(customerId);

			return workingHour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in findAllWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/open/any")
	public ResponseEntity<WorkingHourDto> findAnyOpenWorkingHour(){
		try{
			Optional<WorkingHourDto> workingHour = workingHourManagementPort.findAnyOpenWorkingHour();

			return workingHour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in findAllWorkingHour-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}
}
