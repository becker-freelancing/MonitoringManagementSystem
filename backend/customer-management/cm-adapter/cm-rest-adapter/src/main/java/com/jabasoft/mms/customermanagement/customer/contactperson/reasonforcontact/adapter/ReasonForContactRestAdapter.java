package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

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

import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.api.ReasonForContactPort;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

@RestController
@RequestMapping("/mms/api/customers/contactpersons/reasonforcontact")
class ReasonForContactRestAdapter {

	private ReasonForContactPort reasonForContactPort;

	@Autowired
	public ReasonForContactRestAdapter(ReasonForContactPort reasonForContactPort) {

		this.reasonForContactPort = reasonForContactPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<ReasonForContactDto> saveReason(@RequestBody ReasonForContactDto reason){

		try {
			Optional<ReasonForContactDto> contactPersonPositionDto = reasonForContactPort.saveReason(reason);

			return contactPersonPositionDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.internalServerError().build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{reason}")
	public ResponseEntity<Boolean> deleteReason(@PathVariable("reason") String reason){
		try {
			Optional<ReasonForContactDto> reasonDto = reasonForContactPort.deleteReason(reason);

			if(reasonDto.isPresent()){
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.status(810).build();
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{reason}")
	public ResponseEntity<ReasonForContactDto> getReason(@PathVariable("reason") String reason){

		try{
			Optional<ReasonForContactDto> reasonDto = reasonForContactPort.getReason(reason);

			return reasonDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<ReasonForContactDto>> findAll(){
		try{
			List<ReasonForContactDto> all = reasonForContactPort.getReasons();

			return ResponseEntity.ok(all);
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
}
