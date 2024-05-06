package com.jabasoft.mms.todomanagement.adapter;

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

import com.jabasoft.mms.todomanagement.api.TodoCategoryPort;
import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;

@RestController
@RequestMapping("/mms/api/todo/category")
class TodoCategoryRestAdapter {
	
	private TodoCategoryPort categoryPort;

	@Autowired
	public TodoCategoryRestAdapter(TodoCategoryPort categoryPort) {

		this.categoryPort = categoryPort;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save")
	public ResponseEntity<TodoCategoryDto> saveCategory(@RequestBody TodoCategoryDto todoCategoryDto){
		try {

			return categoryPort.saveCategory(todoCategoryDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{category}")
	public ResponseEntity<TodoCategoryDto> deleteCategory(@PathVariable("category") String category){
		try {

			return categoryPort.deleteCategory(category).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/{category}")
	public ResponseEntity<TodoCategoryDto> getCategory(@PathVariable("category") String category){
		try {

			return categoryPort.getCategory(category).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(810).build());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public ResponseEntity<List<TodoCategoryDto>> findAll(){
		try {

			return ResponseEntity.ok(categoryPort.findAll());
		} catch (Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
