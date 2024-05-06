package com.jabasoft.mms.todomanagement.category.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.todomanagement.MmsDaoImplTest;
import com.jabasoft.mms.todomanagement.domain.model.TodoCategory;
import com.jabasoft.mms.todomanagement.spi.TodoCategoryRepository;

@MmsDaoImplTest
class TodoCategoryDaoTest {


	TodoCategoryRepository todoCategoryDao;

	@Autowired
	public TodoCategoryDaoTest(TodoCategoryRepository todoCategoryDao) {

		this.todoCategoryDao = todoCategoryDao;
	}

	@Test
	void testFindAllWithEmptyDbReturnsEmptyList(){

		List<TodoCategory> actual = todoCategoryDao.findAllTodoCategories();

		assertEquals(0, actual.size());
	}

	@Test
	void testFindAllReturnsAllEntitiesFromDb(){

		List<TodoCategory> expected = createBeans();

		expected.forEach(todoCategoryDao::saveTodoCategory);

		List<TodoCategory> actual = todoCategoryDao.findAllTodoCategories();

		assertFalse(expected.isEmpty());
		assertEquals(expected, actual);
	}

	@Test
	void testFindByReasonReturnsEntityEhenReasonExists(){

		List<TodoCategory> beans = createBeans();

		beans.forEach(todoCategoryDao::saveTodoCategory);

		TodoCategory expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<TodoCategory> actual = todoCategoryDao.findTodoCategory(expected.getCategory());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void testFindByReasonReturnsNoEntityWhenReasonDoesNotExist(){

		List<TodoCategory> beans = createBeans();

		beans.forEach(todoCategoryDao::saveTodoCategory);

		Optional<TodoCategory> actual = todoCategoryDao.findTodoCategory(UUID.randomUUID().toString());

		assertFalse(beans.isEmpty());
		assertTrue(actual.isEmpty());
	}

	@Test
	void deleteByReasonDeletesReasonWhenReasonExists(){
		List<TodoCategory> beans = createBeans();

		beans.forEach(todoCategoryDao::saveTodoCategory);

		TodoCategory expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<TodoCategory> actual = todoCategoryDao.deleteTodoCategory(expected.getCategory());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void deleteByReasonReturnsEmptyOptionalWhenReasonDoesNotExist(){
		List<TodoCategory> beans = createBeans();

		beans.forEach(todoCategoryDao::saveTodoCategory);

		Optional<TodoCategory> actual = todoCategoryDao.deleteTodoCategory(UUID.randomUUID().toString());

		assertTrue(actual.isEmpty());
	}
	
	static List<TodoCategory> createBeans(){

		TodoCategory todoCategory = new TodoCategory();
		todoCategory.setCategory("a713bcf6-f623-458d-93bc-f6f623558d35");

		TodoCategory todoCategory1 = new TodoCategory();
		todoCategory1.setCategory("f1df8f06-02ae-4932-9f8f-0602aea9322a");
		todoCategory1.setDescription("1740f4df-7807-4c47-80f4-df78073c4744");
		todoCategory1.setColor("7c95e2c1-5174-42e7-95e2-c1517402e76d");

		return List.of(todoCategory, todoCategory1);
	}
}