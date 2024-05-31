package com.jabasoft.mms.workinghoursmanagement.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.junit.beans.supplier.setter.RandomSetterBeanCreator;
import com.jabasoft.mms.workinghoursmanagement.domain.model.WorkingHour;

@MmsDaoImplTest
class JpaWorkingHourDaoTest {


	private JpaWorkingHourDao dao;
	private RandomSetterBeanCreator<WorkingHour> workingHourCreator;

	@Autowired
	public JpaWorkingHourDaoTest(JpaWorkingHourDao dao) {

		this.dao = dao;
	}
	
	@BeforeEach
	void setUp(){
		workingHourCreator = new RandomSetterBeanCreator<>() {

			@Override
			protected Class<? extends WorkingHour> getBeanClass() {

				return WorkingHour.class;
			}
		};
	}

	@Test
	void testSaveAndLoadWorkingHours() {

		List<WorkingHour> workingHours = createWorkingHours();

		List<WorkingHour> expectedWorkingHours = new ArrayList<>();
		for (WorkingHour workingHour : workingHours) {
			Optional<WorkingHour> savedWorkingHour = dao.save(workingHour);
			expectedWorkingHours.add(savedWorkingHour.orElseThrow(AssertionFailedError::new));
		}

		for (WorkingHour expected : expectedWorkingHours) {
			Optional<WorkingHour> actual = dao.find(expected.getId());

			assertTrue(actual.isPresent());
			assertEquals(expected, actual.get());
		}
	}

	@Test
	void testDeleteWorkingHourWithExistingWorkingHourReturnsTrue() {

		List<WorkingHour> workingHours = createWorkingHours();

		List<WorkingHour> expectedWorkingHours = new ArrayList<>();
		for (WorkingHour workingHour : workingHours) {
			Optional<WorkingHour> savedWorkingHour = dao.save(workingHour);
			expectedWorkingHours.add(savedWorkingHour.orElseThrow(AssertionFailedError::new));
		}

		WorkingHour deleted = expectedWorkingHours.remove(0);

		assertEquals(2, dao.findAll().size());

		Optional<WorkingHour> isDeleted = dao.delete(deleted.getId());
		List<WorkingHour> allWorkingHourAfterDelete = dao.findAll();

		assertTrue(isDeleted.isPresent());
		assertEquals(1, allWorkingHourAfterDelete.size());
		assertFalse(allWorkingHourAfterDelete.contains(deleted));
		assertEquals(deleted, isDeleted.get());
	}

	@Test
	void testDeleteWorkingHourWithNoExistingWorkingHourReturnFalse() {

		List<WorkingHour> workingHours = createWorkingHours();

		for (WorkingHour workingHour : workingHours) {
			dao.save(workingHour);
		}

		Optional<WorkingHour> isDeleted = dao.delete(Long.MAX_VALUE);

		assertTrue(isDeleted.isEmpty());
	}

	@Test
	void findOpenForCustomerReturnsOpenWorkingHourForCustomerIfExists(){
		List<WorkingHour> workingHours = createWorkingHours();

		for (WorkingHour workingHour : workingHours) {
			dao.save(workingHour);
		}

		Optional<WorkingHour> openForCustomer = dao.findOpenForCustomer(12L);

		assertTrue(openForCustomer.isPresent());
		assertEquals(12, openForCustomer.get().getCustomerId());
	}

	@Test
	void findOpenForCustomerReturnsEmptyOptionalIfNoOpenWorkingHourExists(){
		List<WorkingHour> workingHours = createWorkingHours();

		for (WorkingHour workingHour : workingHours) {
			dao.save(workingHour);
		}

		Optional<WorkingHour> openForCustomer = dao.findOpenForCustomer(13L);

		assertTrue(openForCustomer.isEmpty());
	}


	@Test
	void findAnyOpenReturnsWorkingHourIfOpenWorkingHourExists(){
		List<WorkingHour> workingHours = createWorkingHours();

		for (WorkingHour workingHour : workingHours) {
			dao.save(workingHour);
		}

		Optional<WorkingHour> openForCustomer = dao.findAnyOpenWorkingHour();

		assertTrue(openForCustomer.isPresent());
		assertEquals(12, openForCustomer.get().getCustomerId());
	}

	@Test
	void findAnyOpenReturnsEmptyOptionalIfNoOpenWorkingHourExists(){

		List<WorkingHour> workingHours = createWorkingHours().stream()
			.filter(workingHour -> workingHour.getEndTime() != null)
			.toList();

		for (WorkingHour workingHour : workingHours) {
			dao.save(workingHour);
		}

		Optional<WorkingHour> openForCustomer = dao.findAnyOpenWorkingHour();

		assertTrue(openForCustomer.isEmpty());
	}
	
	List<WorkingHour> createWorkingHours(){

		WorkingHour workingHour = new WorkingHour();
		workingHour.setDate(LocalDate.of(2024, 4, 29));
		workingHour.setStartTime(LocalTime.MIN);
		workingHour.setCustomerId(12L);
		workingHour.setProjectId(18L);
		
		WorkingHour workingHour1 = new WorkingHour();
		workingHour1.setDate(LocalDate.of(2024, 4, 29));
		workingHour1.setStartTime(LocalTime.MIN);
		workingHour1.setEndTime(LocalTime.MAX);
		workingHour1.setCustomerId(13L);
		workingHour1.setProjectId(14L);
		
		return List.of(workingHour1, workingHour);
	}
}