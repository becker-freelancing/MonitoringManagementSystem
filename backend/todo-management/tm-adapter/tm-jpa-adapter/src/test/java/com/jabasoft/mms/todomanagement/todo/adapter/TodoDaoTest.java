package com.jabasoft.mms.todomanagement.todo.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.todomanagement.MmsDaoImplTest;
import com.jabasoft.mms.todomanagement.category.adapter.JpaTodoCategory;
import com.jabasoft.mms.todomanagement.domain.model.Todo;
import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;

@MmsDaoImplTest
class TodoDaoTest {
	
	TodoDao todoDao;

	@Autowired
	public TodoDaoTest(TodoDao todoDao) {

		this.todoDao = todoDao;
	}

	@Test
	void testFindAllWithEmptyDbReturnsEmptyList(){

		List<Todo> actual = todoDao.findAllTodos();

		assertEquals(0, actual.size());
	}

	@Test
	void testFindAllReturnsAllEntitiesFromDb(){

		List<Todo> beans = createBeans();

		List<Todo> expected = new ArrayList<>();
		beans.forEach(todo -> todoDao.saveTodo(todo).ifPresent(expected::add));

		List<Todo> actual = todoDao.findAllTodos();

		assertEquals(2, beans.size());
		assertEquals(expected, actual);
	}

	@Test
	void testFindByReasonReturnsEntityEhenReasonExists(){

		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		Todo expected = todoDao.findAllTodos().stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<Todo> actual = todoDao.findTodo(expected.getTodoId());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void testFindByReasonReturnsNoEntityWhenReasonDoesNotExist(){

		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		Optional<Todo> actual = todoDao.findTodo(123456L);

		assertFalse(beans.isEmpty());
		assertTrue(actual.isEmpty());
	}

	@Test
	void deleteByReasonDeletesReasonWhenReasonExists(){
		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		Todo expected = todoDao.findAllTodos().stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<Todo> actual = todoDao.deleteTodo(expected.getTodoId());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void deleteByReasonReturnsEmptyOptionalWhenReasonDoesNotExist(){
		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		Optional<Todo> actual = todoDao.deleteTodo(123456L);

		assertTrue(actual.isEmpty());
	}

	@Test
	void testDeleteByCustomerIdReturnsEmptyListWhenNoTodoWithCustomerIdExists(){
		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		List<Todo> actual = todoDao.deleteTodosForCustomer(123456L);

		assertTrue(actual.isEmpty());
	}


	@Test
	void testDeleteByCustomerIdReturnsListWhenTodoWithCustomerIdExists() {

		List<Todo> beans = createBeans();

		beans.forEach(todoDao::saveTodo);

		List<Todo> actual = todoDao.deleteTodosForCustomer(12L);

		assertEquals(1, actual.size());

	}

		static List<Todo> createBeans(){

		Todo todo = new Todo();
		todo.setTodoId(1L);
		todo.setTitle("37cc29a3-5335-49a0-8c29-a3533569a006");
		todo.setShortDescription("6545b198-841a-42bd-85b1-98841ac2bda1");
		todo.setLongDescription("cf249ffe-3470-4602-a49f-fe347016024e");
		todo.setCreationTime(LocalDateTime.MIN);
		todo.setEndTime(LocalDateTime.MAX);
		todo.setClosedTime(LocalDateTime.MAX.minusYears(123456L));
		todo.setCustomerId(12L);

		TodoCategory todoCategory = new TodoCategory();
		todoCategory.setCategory("9d22759f-644e-4a91-a275-9f644e8a91f7");
		todoCategory.setDescription("3560e9c5-8fe3-4304-a0e9-c58fe3a3043c");
		todo.setCategory(todoCategory);


		Todo todo1 = new Todo();
		todo1.setTitle("6c538065-aa33-454d-9380-65aa33c54d07");
		todo1.setCreationTime(LocalDateTime.MIN);

		return List.of(todo, todo1);
	}
}