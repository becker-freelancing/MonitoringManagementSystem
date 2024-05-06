package com.jabasoft.mms.todomanagement.category.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;
import com.jabasoft.mms.todomanagement.spi.TodoCategoryRepository;

@Component
class TodoCategoryDao implements TodoCategoryRepository {

	private SpringJpaTodoCategoryRepository repository;

	@Autowired
	public TodoCategoryDao(SpringJpaTodoCategoryRepository repository) {

		this.repository = repository;
	}

	@Override
	public Optional<TodoCategory> deleteTodoCategory(String category) {

		Optional<JpaTodoCategory> byId = repository.findById(category);

		if(byId.isEmpty()){
			return Optional.empty();
		}

		repository.deleteById(category);

		return byId.map(this::mapCategory);
	}

	@Override
	public List<TodoCategory> findAllTodoCategories() {

		Iterable<JpaTodoCategory> all = repository.findAll();

		List<TodoCategory> found = new ArrayList<>();

		for (JpaTodoCategory jpaTodoCategory : all) {
			found.add(mapCategory(jpaTodoCategory));
		}

		return found;
	}

	@Override
	public Optional<TodoCategory> findTodoCategory(String category) {

		Optional<JpaTodoCategory> byId = repository.findById(category);

		return byId.map(this::mapCategory);
	}

	@Override
	public Optional<TodoCategory> saveTodoCategory(TodoCategory todo) {

		JpaTodoCategory saved = repository.save(mapCategory(todo));

		return Optional.of(saved).map(this::mapCategory);
	}

	private JpaTodoCategory mapCategory(TodoCategory category){

		JpaTodoCategory jpaTodoCategory = new JpaTodoCategory();

		jpaTodoCategory.setCategory(category.getCategory());
		jpaTodoCategory.setDescription(category.getDescription());
		jpaTodoCategory.setColor(category.getColor());

		return jpaTodoCategory;
	}

	private TodoCategory mapCategory(JpaTodoCategory jpaTodoCategory){

		TodoCategory todoCategory = new TodoCategory();

		todoCategory.setCategory(jpaTodoCategory.getCategory());
		todoCategory.setDescription(jpaTodoCategory.getDescription());
		todoCategory.setColor(jpaTodoCategory.getColor());

		return todoCategory;
	}
}
