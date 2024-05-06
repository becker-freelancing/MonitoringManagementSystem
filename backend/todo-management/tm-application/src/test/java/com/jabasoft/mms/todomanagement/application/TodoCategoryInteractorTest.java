package com.jabasoft.mms.todomanagement.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;
import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;
import com.jabasoft.mms.todomanagement.spi.TodoCategoryRepository;
import com.jabasoft.mms.todomanagement.spi.TodoRepository;

class TodoCategoryInteractorTest {

	TodoCategoryRepository repository;
	TodoCategoryInteractor interactor;

	@BeforeEach
	void setUp() {

		repository = mock(TodoCategoryRepository.class);
		interactor = new TodoCategoryInteractor(repository);
	}

	@Test
	void testGetTodosWithNoAvailableTodosReturnsEmptyList() {

		when(repository.findAllTodoCategories()).thenReturn(List.of());

		List<TodoCategoryDto> positions = interactor.findAll();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetTodosReturnAllTodos() {

		TodoCategoryDto dto1 = createTodo1();
		TodoCategoryDto dto2 = createTodo2();

		List<TodoCategoryDto> expected = List.of(dto1, dto2);

		when(repository.findAllTodoCategories()).thenReturn(List.of(
			interactor.mapCategory(dto1),
			interactor.mapCategory(dto2)
		));

		List<TodoCategoryDto> actual = interactor.findAll();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0), actual.get(0));
		assertEquals(expected.get(1), actual.get(1));
	}

	@Test
	void testGetTodoReturnsTodoWhenTodoExists() {

		TodoCategoryDto expected = createTodo1();

		when(repository.findTodoCategory(any())).thenReturn(Optional.of(interactor.mapCategory(expected)));

		Optional<TodoCategoryDto> position = interactor.getCategory("1234");

		assertTrue(position.isPresent());

		TodoCategoryDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testGetTodoReturnsEmptyOptionalWhenNoTodoExists() {

		when(repository.findTodoCategory(any())).thenReturn(Optional.empty());

		Optional<TodoCategoryDto> position = interactor.getCategory("1234");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteTodoReturnEmptyOptionalWhenNoTodoExists() {

		when(repository.findTodoCategory(any())).thenReturn(Optional.empty());

		Optional<TodoCategoryDto> position = interactor.deleteCategory("1234");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteTodoReturnTodoWhenTodoExists() {

		TodoCategoryDto expected = createTodo1();

		when(repository.deleteTodoCategory(any())).thenReturn(Optional.of(interactor.mapCategory(expected)));

		Optional<TodoCategoryDto> position = interactor.deleteCategory("1234");

		assertTrue(position.isPresent());

		TodoCategoryDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testSaveTodoReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		TodoCategoryDto dto = createTodo1();

		when(repository.saveTodoCategory(any())).thenReturn(Optional.empty());

		Optional<TodoCategoryDto> position = interactor.saveCategory(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSaveTodoReturnTodoWhenRepositorySaves() {

		TodoCategoryDto expected = createTodo1();

		when(repository.saveTodoCategory(any())).thenReturn(Optional.of(interactor.mapCategory(expected)));

		Optional<TodoCategoryDto> position = interactor.saveCategory(expected);

		assertTrue(position.isPresent());

		TodoCategoryDto actual = position.get();

		assertEquals(expected, actual);
	}

	private static TodoCategoryDto createTodo1() {

		TodoCategoryDto todoCategoryDto = new TodoCategoryDto();
		
		todoCategoryDto.setCategory("1179cfb8-e40a-4e74-b9cf-b8e40a5e7405");
		todoCategoryDto.setDescription("f0825118-0ffe-4b3e-8251-180ffe1b3e08");
		todoCategoryDto.setColor("04a711a3-0c84-4791-a711-a30c84e79129");

		return todoCategoryDto;
	}

	private static TodoCategoryDto createTodo2() {

		TodoCategoryDto todoCategoryDto = new TodoCategoryDto();
		
		todoCategoryDto.setCategory("844a9481-cdbb-4806-8a94-81cdbb380635");
		todoCategoryDto.setDescription("34c02905-d786-4501-8029-05d78675012b");
		todoCategoryDto.setColor("9268afa8-f347-4f13-a8af-a8f3479f139f");

		return todoCategoryDto;
	}
}