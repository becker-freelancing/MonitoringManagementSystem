package com.jabasoft.mms.todomanagement.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.todomanagement.dto.TodoDto;

public interface TodoManagementPort {

	public List<TodoDto> deleteTodoForCustomer(Long customerId);
	public Optional<TodoDto> saveTodo(TodoDto todoDto);
	public Optional<TodoDto> deleteTodo(Long id);
	public Optional<TodoDto> getTodo(Long id);
	public List<TodoDto> findAll();
	public Optional<TodoDto> closeTodo(Long id, LocalDateTime closeTime);
}
