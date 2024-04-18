package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.customermanagement.MmsDaoImplTest;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.junit.beans.RandomBeanCreator;
import com.jabasoft.mms.junit.beans.RandomBeanSupplierRegistry;

@MmsDaoImplTest
class ReasonForContactDaoTest {

	ReasonForContactDao reasonForContactDao;
	RandomBeanCreator<ReasonForContact> reasonForContactCreator;

	@Autowired
	public ReasonForContactDaoTest(ReasonForContactDao reasonForContactDao) {

		this.reasonForContactDao = reasonForContactDao;
	}

	@BeforeAll
	static void clearBeanSupplierRegistry(){

		RandomBeanSupplierRegistry.clear();
	}

	@BeforeEach
	void setUp() {
		reasonForContactCreator = new RandomBeanCreator<ReasonForContact>() {

			@Override
			protected Class<? extends ReasonForContact> getBeanClass() {

				return ReasonForContact.class;
			}
		};
	}

	@Test
	void testFindAllWithEmptyDbReturnsEmptyList(){

		List<ReasonForContact> actual = reasonForContactDao.findAll();

		assertEquals(0, actual.size());
	}

	@Test
	void testFindAllReturnsAllEntitiesFromDb(){

		List<ReasonForContact> expected = reasonForContactCreator.createBeans().toList();

		expected.forEach(reasonForContactDao::saveReason);

		List<ReasonForContact> actual = reasonForContactDao.findAll();

		assertFalse(expected.isEmpty());
		assertEquals(expected, actual);
	}

	@Test
	void testFindByReasonReturnsEntityEhenReasonExists(){

		List<ReasonForContact> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::saveReason);

		ReasonForContact expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<ReasonForContact> actual = reasonForContactDao.findByReason(expected.getReason());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void testFindByReasonReturnsNoEntityWhenReasonDoesNotExist(){

		List<ReasonForContact> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::saveReason);

		Optional<ReasonForContact> actual = reasonForContactDao.findByReason(UUID.randomUUID().toString());

		assertFalse(beans.isEmpty());
		assertTrue(actual.isEmpty());
	}

	@Test
	void deleteByReasonDeletesReasonWhenReasonExists(){
		List<ReasonForContact> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::saveReason);

		ReasonForContact expected = beans.stream().findAny().orElseThrow(() -> new AssertionFailedError("Min one Bean needed"));

		Optional<ReasonForContact> actual = reasonForContactDao.deleteByReason(expected.getReason());

		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

	@Test
	void deleteByReasonReturnsEmptyOptionalWhenReasonDoesNotExist(){
		List<ReasonForContact> beans = reasonForContactCreator.createBeans().toList();

		beans.forEach(reasonForContactDao::saveReason);

		Optional<ReasonForContact> actual = reasonForContactDao.deleteByReason(UUID.randomUUID().toString());

		assertTrue(actual.isEmpty());
	}
}