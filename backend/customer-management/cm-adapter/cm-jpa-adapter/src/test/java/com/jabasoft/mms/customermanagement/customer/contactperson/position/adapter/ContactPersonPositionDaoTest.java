package com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.customermanagement.MmsDaoImplTest;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.junit.beans.RandomBeanCreator;

@MmsDaoImplTest
class ContactPersonPositionDaoTest {


	ContactPersonPositionDao reasonForContactDao;
	RandomBeanCreator<ContactPersonPosition> reasonForContactCreator;

	@Autowired
	public ContactPersonPositionDaoTest(ContactPersonPositionDao reasonForContactDao) {

		this.reasonForContactDao = reasonForContactDao;
	}

	@BeforeEach
	void setUp() {
		reasonForContactCreator = new RandomBeanCreator<ContactPersonPosition>() {

			@Override
			protected Class<? extends ContactPersonPosition> getBeanClass() {

				return ContactPersonPosition.class;
			}
		};
	}

	@Test
	void testFindAllWithEmptyDbReturnsEmptyList(){

		List<ContactPersonPosition> actual = reasonForContactDao.findAll();

		assertEquals(0, actual.size());
	}

	@Test
	void testFindAllReturnsAllEntitiesFromDb(){

		List<ContactPersonPosition> expected = reasonForContactCreator.createBeans().toList();

		expected.forEach(reasonForContactDao::savePosition);

		List<ContactPersonPosition> actual = reasonForContactDao.findAll();

		assertFalse(expected.isEmpty());
		assertEquals(expected, actual);
	}

	@Test
	void testFindByReasonReturnsEntityEhenReasonExists(){

		List<ContactPersonPosition> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::savePosition);

		ContactPersonPosition expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<ContactPersonPosition> actual = reasonForContactDao.findByPosition(expected.getPosition());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void testFindByReasonReturnsNoEntityWhenReasonDoesNotExist(){

		List<ContactPersonPosition> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::savePosition);

		Optional<ContactPersonPosition> actual = reasonForContactDao.findByPosition(UUID.randomUUID().toString());

		assertFalse(beans.isEmpty());
		assertTrue(actual.isEmpty());
	}

	@Test
	void deleteByReasonDeletesReasonWhenReasonExists(){
		List<ContactPersonPosition> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::savePosition);

		ContactPersonPosition expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<ContactPersonPosition> actual = reasonForContactDao.deleteByPosition(expected.getPosition());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void deleteByReasonReturnsEmptyOptionalWhenReasonDoesNotExist(){
		List<ContactPersonPosition> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::savePosition);

		Optional<ContactPersonPosition> actual = reasonForContactDao.deleteByPosition(UUID.randomUUID().toString());

		assertTrue(actual.isEmpty());
	}
}