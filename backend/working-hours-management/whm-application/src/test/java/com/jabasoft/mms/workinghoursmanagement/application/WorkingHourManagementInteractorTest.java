package com.jabasoft.mms.workinghoursmanagement.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.workinghoursmanagement.domain.model.WorkingHour;
import com.jabasoft.mms.workinghoursmanagement.dto.WorkingHourDto;
import com.jabasoft.mms.workinghoursmanagement.spi.WorkingHourRepository;

class WorkingHourManagementInteractorTest {

	private WorkingHourRepository repository;
	private WorkingHourManagementInteractor interactor;

	@BeforeEach
	void setUp() {

		repository = mock(WorkingHourRepository.class);
		interactor = new WorkingHourManagementInteractor(repository);
	}

	@Test
	void testSaveWorkingHourReturnsWorkingHourOnSuccess() {

		WorkingHour expected = createWorkingHourAsDomain();

		when(repository.save(any())).thenReturn(Optional.of(expected));

		Optional<WorkingHourDto> saved = interactor.save(createWorkingHourAsDto());

		assertTrue(saved.isPresent());

		WorkingHourDto actual = saved.get();

		assertEquals(expected.getId(), actual.getId(), "Id");
		assertEquals(expected.getDate(), actual.getDate(), "Date");
		assertEquals(expected.getStartTime(), actual.getStartTime(), "StartTime");
		assertEquals(expected.getEndTime(), actual.getEndTime(), "EndTime");
		assertEquals(expected.getCustomerId(), actual.getCustomerId(), "CustomerId");
		assertEquals(expected.getProjectId(), actual.getProjectId(), "ProjectId");
	}

	@Test
	void testSaveWorkingHourReturnsEmptyOptionalOnFail() {

		when(repository.save(any())).thenReturn(Optional.empty());

		Optional<WorkingHourDto> saved = interactor.save(createWorkingHourAsDto());

		assertTrue(saved.isEmpty());
	}

	@Test
	void testFindAllReturnsListWithMultipleWorkingHours() {

		WorkingHour expected1 = createWorkingHourAsDomain();
		WorkingHour expected2 = createWorkingHourAsDomain();

		when(repository.findAll()).thenReturn(List.of(expected1, expected2));

		List<WorkingHourDto> find = interactor.findAll();

		assertEquals(2, find.size());

		WorkingHourDto actual1 = find.get(0);



		assertEquals(expected1.getId(), actual1.getId(), "Id");
		assertEquals(expected1.getDate(), actual1.getDate(), "Date");
		assertEquals(expected1.getStartTime(), actual1.getStartTime(), "StartTime");
		assertEquals(expected1.getEndTime(), actual1.getEndTime(), "EndTime");
		assertEquals(expected1.getCustomerId(), actual1.getCustomerId(), "CustomerId");
		assertEquals(expected1.getProjectId(), actual1.getProjectId(), "ProjectId");

		WorkingHourDto actual2 = find.get(1);


		assertEquals(expected2.getId(), actual2.getId(), "Id");
		assertEquals(expected2.getDate(), actual2.getDate(), "Date");
		assertEquals(expected2.getStartTime(), actual2.getStartTime(), "StartTime");
		assertEquals(expected2.getEndTime(), actual2.getEndTime(), "EndTime");
		assertEquals(expected2.getCustomerId(), actual2.getCustomerId(), "CustomerId");
		assertEquals(expected2.getProjectId(), actual2.getProjectId(), "ProjectId");
	}

	@Test
	void testFindAllReturnsEmptyListWhenNoWorkingHourExists() {
		when(repository.findAll()).thenReturn(List.of());

		List<WorkingHourDto> find = interactor.findAll();

		assertEquals(0, find.size());
	}

	@Test
	void testDeleteWorkingHourDeletesWorkingHourWhenWorkingHourExists() {
		WorkingHour expected = createWorkingHourAsDomain();

		when(repository.delete(any())).thenReturn(Optional.of(expected));

		Optional<WorkingHourDto> deleted = interactor.delete(expected.getId());

		assertTrue(deleted.isPresent());

		WorkingHourDto actual = deleted.get();


		assertEquals(expected.getId(), actual.getId(), "Id");
		assertEquals(expected.getDate(), actual.getDate(), "Date");
		assertEquals(expected.getStartTime(), actual.getStartTime(), "StartTime");
		assertEquals(expected.getEndTime(), actual.getEndTime(), "EndTime");
		assertEquals(expected.getCustomerId(), actual.getCustomerId(), "CustomerId");
		assertEquals(expected.getProjectId(), actual.getProjectId(), "ProjectId");
	}

	@Test
	void testDeleteWorkingHourReturnsEmptyOptionalWhenNoWorkingHourExists() {
		when(repository.delete(any())).thenReturn(Optional.empty());

		Optional<WorkingHourDto> deleted = interactor.delete(12L);

		assertTrue(deleted.isEmpty());
	}

	@Test
	void testGetWorkingHourReturnsWorkingHourWhenWorkingHourExists() {
		WorkingHour expected = createWorkingHourAsDomain();

		when(repository.find(any())).thenReturn(Optional.of(expected));

		Optional<WorkingHourDto> find = interactor.find(expected.getId());

		assertTrue(find.isPresent());

		WorkingHourDto actual = find.get();


		assertEquals(expected.getId(), actual.getId(), "Id");
		assertEquals(expected.getDate(), actual.getDate(), "Date");
		assertEquals(expected.getStartTime(), actual.getStartTime(), "StartTime");
		assertEquals(expected.getEndTime(), actual.getEndTime(), "EndTime");
		assertEquals(expected.getCustomerId(), actual.getCustomerId(), "CustomerId");
		assertEquals(expected.getProjectId(), actual.getProjectId(), "ProjectId");
	}

	@Test
	void testGetWorkingHourReturnsEmptyOptionalWhenNoWorkingHourExists() {
		when(repository.find(any())).thenReturn(Optional.empty());

		Optional<WorkingHourDto> find = interactor.find(12L);

		assertTrue(find.isEmpty());
	}

	@Test
	void findAnyOpenReturnsWorkingHourIfOpenWorkingHourExists(){

		when(repository.findAnyOpenWorkingHour()).thenReturn(Optional.of(createOpenWorkingHourAsDomain()));

		Optional<WorkingHourDto> openForCustomer = interactor.findAnyOpenWorkingHour();

		assertTrue(openForCustomer.isPresent());
		assertEquals(18, openForCustomer.get().getCustomerId());
	}

	@Test
	void findAnyOpenReturnsEmptyOptionalIfNoOpenWorkingHourExists(){

		when(repository.findAnyOpenWorkingHour()).thenReturn(Optional.empty());

		Optional<WorkingHourDto> openForCustomer = interactor.findAnyOpenWorkingHour();

		assertTrue(openForCustomer.isEmpty());
	}

	@Test
	void findOpenForCustomerReturnsWorkingHourIfOpenWorkingHourExists(){

		when(repository.findOpenForCustomer(18L)).thenReturn(Optional.of(createOpenWorkingHourAsDomain()));

		Optional<WorkingHourDto> openForCustomer = interactor.findCurrentOpenForCustomer(18L);

		assertTrue(openForCustomer.isPresent());
		assertEquals(18, openForCustomer.get().getCustomerId());
	}

	@Test
	void findOpenForCustomerReturnsEmptyOptionalIfNoOpenWorkingHourExists(){

		when(repository.findOpenForCustomer(any())).thenReturn(Optional.empty());

		Optional<WorkingHourDto> openForCustomer = interactor.findCurrentOpenForCustomer(18L);

		assertTrue(openForCustomer.isEmpty());
	}

	private WorkingHour createWorkingHourAsDomain() {

		WorkingHour workingHour = new WorkingHour();

		workingHour.setId(1L);
		workingHour.setDate(LocalDate.of(2024, 4, 19));
		workingHour.setStartTime(LocalTime.of(12, 34, 0));
		workingHour.setEndTime(LocalTime.MAX);
		workingHour.setCustomerId(18L);
		workingHour.setProjectId(12L);

		return workingHour;
	}

	private WorkingHour createOpenWorkingHourAsDomain() {

		WorkingHour workingHour = new WorkingHour();

		workingHour.setId(1L);
		workingHour.setDate(LocalDate.of(2024, 4, 29));
		workingHour.setStartTime(LocalTime.of(12, 34, 0));
		workingHour.setCustomerId(18L);
		workingHour.setProjectId(12L);

		return workingHour;
	}

	private WorkingHourDto createWorkingHourAsDto() {

		WorkingHourDto workingHour = new WorkingHourDto();

		workingHour.setId(1L);
		workingHour.setDate(LocalDate.of(2024, 4, 29));
		workingHour.setStartTime(LocalTime.of(12, 34, 0));
		workingHour.setEndTime(LocalTime.MAX);
		workingHour.setCustomerId(18L);
		workingHour.setProjectId(12L);

		return workingHour;
	}
}