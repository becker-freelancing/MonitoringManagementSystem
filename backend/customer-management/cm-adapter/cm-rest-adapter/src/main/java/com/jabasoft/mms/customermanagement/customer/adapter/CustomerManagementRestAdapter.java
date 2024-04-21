package com.jabasoft.mms.customermanagement.customer.adapter;

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

import com.jabasoft.mms.customermanagement.customer.api.CustomerManagementPort;
import com.jabasoft.mms.customermanagement.dto.CustomerDto;


@RestController
@RequestMapping("/mms/api/customers")
class CustomerManagementRestAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManagementRestAdapter.class);

	private CustomerManagementPort customerManagementPort;

	@Autowired
	public CustomerManagementRestAdapter(CustomerManagementPort customerManagementPort) {

		this.customerManagementPort = customerManagementPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customer){

		try {
			Optional<CustomerDto> customerDto = customerManagementPort.saveCustomer(customer);

			return customerDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in saveCustomer-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("id") Long id){
		try {
			Optional<CustomerDto> deleted = customerManagementPort.deleteCustomer(id);

			return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());

		} catch (Exception e){
			LOGGER.error("Exception in deleteCustomer-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id){

		try{
			Optional<CustomerDto> customer = customerManagementPort.getCustomer(id);

			return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			LOGGER.error("Exception in getCustomer-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<CustomerDto>> findAll(){
		try{
			List<CustomerDto> all = customerManagementPort.findAll();

			return ResponseEntity.ok(all);
		} catch (Exception e){
			LOGGER.error("Exception in findAllCustomer-Request", e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
