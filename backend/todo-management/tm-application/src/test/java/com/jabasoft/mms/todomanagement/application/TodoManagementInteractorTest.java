package com.jabasoft.mms.todomanagement.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.todomanagement.domain.model.Todo;
import com.jabasoft.mms.todomanagement.dto.TodoCategoryDto;
import com.jabasoft.mms.todomanagement.dto.TodoDto;
import com.jabasoft.mms.todomanagement.spi.TodoRepository;

class TodoManagementInteractorTest {

	TodoRepository repository;
	TodoManagementInteractor interactor;

	@BeforeEach
	void setUp() {

		repository = mock(TodoRepository.class);
		interactor = new TodoManagementInteractor(repository);
	}

	@Test
	void testGetTodosWithNoAvailableTodosReturnsEmptyList() {

		when(repository.findAllTodos()).thenReturn(List.of());

		List<TodoDto> positions = interactor.findAll();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetTodosReturnAllTodos() {

		TodoDto dto1 = createTodo1();
		TodoDto dto2 = createTodo2();

		List<TodoDto> expected = List.of(dto1, dto2);

		when(repository.findAllTodos()).thenReturn(List.of(
			interactor.mapTodo(dto1),
			interactor.mapTodo(dto2)
		));

		List<TodoDto> actual = interactor.findAll();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0), actual.get(0));
		assertEquals(expected.get(1), actual.get(1));
	}

	@Test
	void testGetTodoReturnsTodoWhenTodoExists() {

		TodoDto expected = createTodo1();

		when(repository.findTodo(any())).thenReturn(Optional.of(interactor.mapTodo(expected)));

		Optional<TodoDto> position = interactor.getTodo(1234L);

		assertTrue(position.isPresent());

		TodoDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testGetTodoReturnsEmptyOptionalWhenNoTodoExists() {

		when(repository.findTodo(any())).thenReturn(Optional.empty());

		Optional<TodoDto> position = interactor.getTodo(1234L);

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteTodoReturnEmptyOptionalWhenNoTodoExists() {

		when(repository.findTodo(any())).thenReturn(Optional.empty());

		Optional<TodoDto> position = interactor.deleteTodo(1234L);

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteTodoReturnTodoWhenTodoExists() {

		TodoDto expected = createTodo1();

		when(repository.deleteTodo(any())).thenReturn(Optional.of(interactor.mapTodo(expected)));

		Optional<TodoDto> position = interactor.deleteTodo(1234L);

		assertTrue(position.isPresent());

		TodoDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testSaveTodoReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		TodoDto dto = createTodo1();

		when(repository.saveTodo(any())).thenReturn(Optional.empty());

		Optional<TodoDto> position = interactor.saveTodo(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSaveTodoReturnTodoWhenRepositorySaves() {

		TodoDto expected = createTodo1();

		when(repository.saveTodo(any())).thenReturn(Optional.of(interactor.mapTodo(expected)));

		Optional<TodoDto> position = interactor.saveTodo(expected);

		assertTrue(position.isPresent());

		TodoDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testCloseTodoWithNonExistingTodoReturnsEmptyOptional(){

		Optional<TodoDto> closedTodo = interactor.closeTodo(123456L, LocalDateTime.MAX);

		assertTrue(closedTodo.isEmpty());
	}

	@Test
	void testCloseTodoWithExistingTodoClosesTodo(){

		Todo emptyTodo = new Todo();

		when(repository.findTodo(1L)).thenReturn(Optional.of(emptyTodo));
		emptyTodo.setClosedTime(LocalDateTime.MAX);
		when(repository.saveTodo(any())).thenReturn(Optional.of(emptyTodo));

		Optional<TodoDto> closedTodo = interactor.closeTodo(1L, LocalDateTime.MAX);

		assertTrue(closedTodo.isPresent());
		assertEquals(LocalDateTime.MAX, closedTodo.get().getClosedTime());
	}


	@Test
	void testDeleteByCustomerIdReturnsEmptyListWhenNoTodoWithCustomerIdExists(){
		when(repository.deleteTodosForCustomer(any())).thenReturn(List.of());

		List<TodoDto> actual = interactor.deleteTodoForCustomer(123456L);

		assertTrue(actual.isEmpty());
	}


	@Test
	void testDeleteByCustomerIdReturnsListWhenTodoWithCustomerIdExists() {

		when(repository.deleteTodosForCustomer(any())).thenReturn(List.of(
			interactor.mapTodo(createTodo1()),
			interactor.mapTodo(createTodo2())));

		List<TodoDto> actual = interactor.deleteTodoForCustomer(123456L);

		assertEquals(2, actual.size());

	}

	private static TodoDto createTodo1() {

		TodoDto todo = new TodoDto();

		todo.setCategory(new TodoCategoryDto());
		todo.setClosedTime(LocalDateTime.MAX);
		todo.setCreationTime(LocalDateTime.MIN);
		todo.setCustomerId(12L);
		todo.setEndTime(LocalDateTime.MAX.minusYears(120));
		todo.setLongDescription("56edb1fc-da22-4f87-adb1-fcda229f8745");
		todo.setShortDescription("92feeb3b-4f69-4813-beeb-3b4f693813fb");
		todo.setTitle("ebde176a-a04f-41ee-9e17-6aa04f11eedf");
		todo.setTodoId(34L);

		return todo;
	}

	private static TodoDto createTodo2() {

		TodoDto todo = new TodoDto();

		todo.setCategory(new TodoCategoryDto());
		todo.setClosedTime(LocalDateTime.MAX);
		todo.setCreationTime(LocalDateTime.MIN);
		todo.setCustomerId(11342L);
		todo.setEndTime(LocalDateTime.MAX.minusYears(12130));
		todo.setLongDescription("4c4f107b-61a7-4bb1-8f10-7b61a7dbb1bb");
		todo.setShortDescription("a10a4163-9e5b-4c0d-8a41-639e5bec0dd4");
		todo.setTitle("c41fef06-7acf-495c-9fef-067acfe95c71");
		todo.setTodoId(3413L);

		return todo;
	}

}