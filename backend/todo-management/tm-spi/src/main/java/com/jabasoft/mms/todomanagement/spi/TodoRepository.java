package com.jabasoft.mms.todomanagement.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.todomanagement.domain.model.Todo;

public interface TodoRepository {

	public Optional<Todo> deleteTodo(Long id);
	public List<Todo> findAllTodos();
	public Optional<Todo> findTodo(Long id);
	public Optional<Todo> saveTodo(Todo todo);

}
