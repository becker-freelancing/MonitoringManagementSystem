package com.jabasoft.mms.todomanagement.todo.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.todomanagement.category.adapter.JpaTodoCategory;
import com.jabasoft.mms.todomanagement.domain.model.Todo;
import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;
import com.jabasoft.mms.todomanagement.spi.TodoCategoryRepository;
import com.jabasoft.mms.todomanagement.spi.TodoRepository;

@Component
class TodoDao implements TodoRepository {

	SpringJpaTodoRepository repository;

	@Autowired
	public TodoDao(SpringJpaTodoRepository repository){

		this.repository = repository;
	}

	@Override
	public Optional<Todo> deleteTodo(Long id) {

		Optional<JpaTodo> byId = repository.findById(id);

		if (byId.isEmpty()){
			return Optional.empty();
		}

		repository.deleteById(id);

		return byId.map(this::mapTodo);
	}

	@Override
	public List<Todo> deleteTodosForCustomer(Long customerId) {

		List<JpaTodo> deleted = repository.deleteJpaTodoByCustomerId(customerId);

		return deleted.stream().map(this::mapTodo).toList();
	}

	@Override
	public List<Todo> findAllTodos() {

		List<Todo> all = new ArrayList<>();

		for (JpaTodo jpaTodo : repository.findAll()) {
			all.add(mapTodo(jpaTodo));
		}

		return all;
	}

	@Override
	public Optional<Todo> findTodo(Long id) {

		Optional<JpaTodo> byId = repository.findById(id);

		return byId.map(this::mapTodo);
	}

	@Override
	public Optional<Todo> saveTodo(Todo todo) {

		JpaTodo jpaTodo = mapTodo(todo);

		JpaTodo saved = repository.save(jpaTodo);

		return Optional.of(saved).map(this::mapTodo);
	}

	private JpaTodo mapTodo(Todo todo){

		JpaTodo jpaTodo = new JpaTodo();
		jpaTodo.setTodoId(todo.getTodoId());
		jpaTodo.setTitle(todo.getTitle());
		jpaTodo.setShortDescription(todo.getShortDescription());
		jpaTodo.setLongDescription(todo.getLongDescription());
		jpaTodo.setCreationTime(todo.getCreationTime());
		jpaTodo.setEndTime(todo.getEndTime());
		jpaTodo.setClosedTime(todo.getClosedTime());
		jpaTodo.setCustomerId(todo.getCustomerId());

		if(todo.getCategory() != null) {
			JpaTodoCategory jpaTodoCategory = new JpaTodoCategory();
			jpaTodoCategory.setCategory(Optional.ofNullable(todo.getCategory()).map(TodoCategory::getCategory).orElse(null));
			jpaTodoCategory.setDescription(Optional.ofNullable(todo.getCategory()).map(TodoCategory::getDescription).orElse(null));
			jpaTodoCategory.setColor(todo.getCategory().getColor());
			jpaTodo.setCategory(jpaTodoCategory);
		}

		return jpaTodo;
	}

	private Todo mapTodo(JpaTodo jpaTodo){

		Todo todo = new Todo();
		todo.setTodoId(jpaTodo.getTodoId());
		todo.setTitle(jpaTodo.getTitle());
		todo.setShortDescription(jpaTodo.getShortDescription());
		todo.setLongDescription(jpaTodo.getLongDescription());
		todo.setCreationTime(jpaTodo.getCreationTime());
		todo.setEndTime(jpaTodo.getEndTime());
		todo.setClosedTime(jpaTodo.getClosedTime());
		todo.setCustomerId(jpaTodo.getCustomerId());

		if(jpaTodo.getCategory() != null) {
			TodoCategory todoCategory = new TodoCategory();
			todoCategory.setCategory(Optional.ofNullable(jpaTodo.getCategory()).map(JpaTodoCategory::getCategory)
				.orElse(null));
			todoCategory.setDescription(Optional.ofNullable(jpaTodo.getCategory()).map(JpaTodoCategory::getDescription)
				.orElse(null));
			todoCategory.setColor(jpaTodo.getCategory().getColor());
			todo.setCategory(todoCategory);
		}

		return todo;
	}
}
