package com.jabasoft.mms.todomanagement.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.todomanagement.api.TodoCategoryPort;
import com.jabasoft.mms.todomanagement.domain.model.Todo;
import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;
import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;
import com.jabasoft.mms.todomanagement.spi.TodoCategoryRepository;

@Component
class TodoCategoryInteractor implements TodoCategoryPort {
	
	private TodoCategoryRepository categoryRepository;

	@Autowired
	public TodoCategoryInteractor(TodoCategoryRepository categoryRepository) {

		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<TodoCategoryDto> saveCategory(TodoCategoryDto todoCategoryDto) {

		TodoCategory category = mapCategory(todoCategoryDto);
		Optional<TodoCategory> savedTodoCategory = categoryRepository.saveTodoCategory(category);

		return savedTodoCategory.map(this::mapCategory);
	}

	@Override
	public Optional<TodoCategoryDto> deleteCategory(String category) {

		Optional<TodoCategory> deletedTodoCategory = categoryRepository.deleteTodoCategory(category);

		return deletedTodoCategory.map(this::mapCategory);
	}

	@Override
	public Optional<TodoCategoryDto> getCategory(String category) {

		Optional<TodoCategory> foundCategory = categoryRepository.findTodoCategory(category);

		return foundCategory.map(this::mapCategory);
	}

	@Override
	public List<TodoCategoryDto> findAll() {

		List<TodoCategory> categories = categoryRepository.findAllTodoCategories();

		return categories.stream().map(this::mapCategory).toList();
	}

	protected TodoCategoryDto mapCategory(TodoCategory category){

		if(category == null){
			return null;
		}

		TodoCategoryDto todoCategoryDto = new TodoCategoryDto();

		todoCategoryDto.setCategory(category.getCategory());
		todoCategoryDto.setDescription(category.getDescription());
		todoCategoryDto.setColor(category.getColor());

		return todoCategoryDto;
	}

	protected TodoCategory mapCategory(TodoCategoryDto categoryDto){

		if(categoryDto == null){
			return null;
		}

		TodoCategory todoCategory = new TodoCategory();

		todoCategory.setCategory(categoryDto.getCategory());
		todoCategory.setDescription(categoryDto.getDescription());
		todoCategory.setColor(categoryDto.getColor());

		return todoCategory;
	}

}
