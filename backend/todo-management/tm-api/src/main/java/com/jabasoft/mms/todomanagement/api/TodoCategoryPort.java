package com.jabasoft.mms.todomanagement.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;

public interface TodoCategoryPort {

	public Optional<TodoCategoryDto> saveCategory(TodoCategoryDto todoCategoryDto);
	public Optional<TodoCategoryDto> deleteCategory(String category);
	public Optional<TodoCategoryDto> getCategory(String category);
	public List<TodoCategoryDto> findAll();

}
