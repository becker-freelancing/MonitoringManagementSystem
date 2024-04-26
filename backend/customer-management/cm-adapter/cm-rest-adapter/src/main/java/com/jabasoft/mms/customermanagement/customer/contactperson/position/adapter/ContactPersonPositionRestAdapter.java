package com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter;

import java.util.List;
import java.util.Optional;

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

import com.jabasoft.mms.customermanagement.customer.contactperson.position.api.ContactPersonPositionPort;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;

@RestController
@RequestMapping("/mms/api/customers/contactpersons/positions")
class ContactPersonPositionRestAdapter {

	private ContactPersonPositionPort personPositionPort;

	@Autowired
	public ContactPersonPositionRestAdapter(ContactPersonPositionPort personPositionPort) {

		this.personPositionPort = personPositionPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<ContactPersonPositionDto> savePosition(@RequestBody ContactPersonPositionDto position){

		try {
			Optional<ContactPersonPositionDto> contactPersonPositionDto = personPositionPort.savePosition(position);

			return contactPersonPositionDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.internalServerError().build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{position}")
	public ResponseEntity<Boolean> deletePosition(@PathVariable("position") String position){
		try {
			Optional<ContactPersonPositionDto> positionDto = personPositionPort.deletePosition(position);

			if(positionDto.isPresent()){
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.status(810).build();
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{position}")
	public ResponseEntity<ContactPersonPositionDto> getPosition(@PathVariable("position") String position){

		try{
			Optional<ContactPersonPositionDto> positionDto = personPositionPort.getPosition(position);

			return positionDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<ContactPersonPositionDto>> findAll(){
		try{
			List<ContactPersonPositionDto> all = personPositionPort.getPositions();

			return ResponseEntity.ok(all);
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
}
