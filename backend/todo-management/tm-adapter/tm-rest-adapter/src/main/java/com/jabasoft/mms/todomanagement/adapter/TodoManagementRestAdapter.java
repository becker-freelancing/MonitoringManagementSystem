package com.jabasoft.mms.todomanagement.adapter;

import java.time.LocalDateTime;
import java.util.List;

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

import com.jabasoft.mms.todomanagement.api.TodoManagementPort;
import com.jabasoft.mms.todomanagement.dto.TodoDto;

@RestController
@RequestMapping("/mms/api/todo")
class TodoManagementRestAdapter {

	private TodoManagementPort todoManagementPort;

	@Autowired
	public TodoManagementRestAdapter(TodoManagementPort todoManagementPort) {

		this.todoManagementPort = todoManagementPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto){
		try {

			return todoManagementPort.saveTodo(todoDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TodoDto> deleteTodo(@PathVariable("id") Long id){
		try {

			return todoManagementPort.deleteTodo(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id){
		try {

			return todoManagementPort.getTodo(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<TodoDto>> findAll(){
		try {

			return ResponseEntity.ok(todoManagementPort.findAll());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/close/{id}/{time}")
	public ResponseEntity<TodoDto> closeTodo(@PathVariable("id") Long id, @PathVariable("time") String closeTimeAsString){
		try {
			LocalDateTime closeTime = LocalDateTime.parse(closeTimeAsString.replace("\"", "").replace("Z", ""));
			return todoManagementPort.closeTodo(id, closeTime).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/customer/{customerId}")
	public ResponseEntity<List<TodoDto>> closeTodoForCustomer(@PathVariable("customerId") Long customerId){
		try {

			return ResponseEntity.ok(todoManagementPort.deleteTodoForCustomer(customerId));
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

}
