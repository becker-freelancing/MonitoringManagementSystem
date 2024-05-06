package com.jabasoft.mms.todomanagement.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;

public interface TodoCategoryRepository {

	Optional<TodoCategory> deleteTodoCategory(String category);
	List<TodoCategory> findAllTodoCategories();
	Optional<TodoCategory> findTodoCategory(String category);
	Optional<TodoCategory> saveTodoCategory(TodoCategory todo);

}
