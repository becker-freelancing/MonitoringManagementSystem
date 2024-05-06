package com.jabasoft.mms.todomanagement.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.todomanagement.api.TodoManagementPort;
import com.jabasoft.mms.todomanagement.domain.model.Todo;
import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;
import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;
import com.jabasoft.mms.todomanagement.dto.TodoDto;
import com.jabasoft.mms.todomanagement.spi.TodoRepository;

@Component
class TodoManagementInteractor implements TodoManagementPort {

	private TodoRepository todoRepository;

	@Autowired
	public TodoManagementInteractor(TodoRepository todoRepository) {

		this.todoRepository = todoRepository;
	}

	@Override
	public Optional<TodoDto> saveTodo(TodoDto todoDto) {

		Todo todo = mapTodo(todoDto);
		Optional<Todo> savedTodo = todoRepository.saveTodo(todo);

		return savedTodo.map(this::mapTodo);
	}

	@Override
	public Optional<TodoDto> deleteTodo(Long id) {

		Optional<Todo> todo = todoRepository.deleteTodo(id);

		return todo.map(this::mapTodo);
	}

	@Override
	public Optional<TodoDto> getTodo(Long id) {

		Optional<Todo> todo = todoRepository.findTodo(id);

		return todo.map(this::mapTodo);
	}

	@Override
	public List<TodoDto> findAll() {

		List<Todo> todos = todoRepository.findAllTodos();

		return todos.stream().map(this::mapTodo).toList();
	}

	@Override
	public Optional<TodoDto> closeTodo(Long id, LocalDateTime closeTime) {

		Optional<TodoDto> optionalTodo = getTodo(id);

		if(optionalTodo.isEmpty()){
			return Optional.empty();
		}

		TodoDto todoDto = optionalTodo.get();
		todoDto.setClosedTime(closeTime);

		return saveTodo(todoDto);
	}

	protected Todo mapTodo(TodoDto todoDto){

		Todo todo = new Todo();

		todo.setCategory(mapCategory(todoDto.getCategory()));
		todo.setClosedTime(todoDto.getClosedTime());
		todo.setCreationTime(todoDto.getCreationTime());
		todo.setCustomerId(todoDto.getCustomerId());
		todo.setEndTime(todoDto.getEndTime());
		todo.setLongDescription(todoDto.getLongDescription());
		todo.setShortDescription(todoDto.getShortDescription());
		todo.setTitle(todoDto.getTitle());
		todo.setTodoId(todoDto.getTodoId());

		return todo;
	}

	private TodoCategory mapCategory(TodoCategoryDto categoryDto){

		if(categoryDto == null){
			return null;
		}

		TodoCategory todoCategory = new TodoCategory();

		todoCategory.setCategory(categoryDto.getCategory());
		todoCategory.setDescription(categoryDto.getDescription());
		todoCategory.setColor(categoryDto.getColor());

		return todoCategory;
	}

	protected TodoDto mapTodo(Todo todo){

		TodoDto todoDto = new TodoDto();

		todoDto.setCategory(mapCategory(todo.getCategory()));
		todoDto.setClosedTime(todo.getClosedTime());
		todoDto.setCreationTime(todo.getCreationTime());
		todoDto.setCustomerId(todo.getCustomerId());
		todoDto.setEndTime(todo.getEndTime());
		todoDto.setLongDescription(todo.getLongDescription());
		todoDto.setShortDescription(todo.getShortDescription());
		todoDto.setTitle(todo.getTitle());
		todoDto.setTodoId(todo.getTodoId());

		return todoDto;
	}

	private TodoCategoryDto mapCategory(TodoCategory category){

		if(category == null){
			return null;
		}

		TodoCategoryDto todoCategoryDto = new TodoCategoryDto();

		todoCategoryDto.setCategory(category.getCategory());
		todoCategoryDto.setDescription(category.getDescription());
		todoCategoryDto.setColor(category.getColor());

		return todoCategoryDto;
	}
}
