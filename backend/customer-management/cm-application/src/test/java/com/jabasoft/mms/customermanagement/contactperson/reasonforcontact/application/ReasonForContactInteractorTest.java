package com.jabasoft.mms.customermanagement.contactperson.reasonforcontact.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.spi.ReasonForContactRepository;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

class ReasonForContactInteractorTest {
	
	ReasonForContactRepository repository;
	ReasonForContactInteractor interactor;

	@BeforeEach
	void setUp(){
		repository = mock(ReasonForContactRepository.class);
		interactor = new ReasonForContactInteractor(repository);
	}

	@Test
	void testGetReasonWithNoAvailableReasonsReturnsEmptyList() {

		when(repository.findAll()).thenReturn(List.of());

		List<ReasonForContactDto> positions = interactor.getReasons();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetReasonsReturnAllReasons(){

		ReasonForContactDto dto1 = new ReasonForContactDto();
		dto1.setReason("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto1.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		ReasonForContactDto dto2 = new ReasonForContactDto();
		dto1.setReason("e9285b53-69c5-4dcb-a85b-5369c5ddcbc4");

		List<ReasonForContactDto> expected = List.of(dto1, dto2);

		when(repository.findAll()).thenReturn(List.of(
			new ReasonForContact(dto1.getReason(), dto1.getDescription()),
			new ReasonForContact(dto2.getReason(), dto2.getDescription())
		));


		List<ReasonForContactDto> actual = interactor.getReasons();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0).getReason(), actual.get(0).getReason());
		assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
		assertEquals(expected.get(1).getReason(), actual.get(1).getReason());
		assertEquals(expected.get(1).getDescription(), actual.get(1).getDescription());
	}

	@Test
	void testGetReasonReturnsReasonWhenReasonExists() {

		ReasonForContactDto expected = new ReasonForContactDto();
		expected.setReason("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.findByReason(any())).thenReturn(Optional.of(new ReasonForContact(expected.getReason(), expected.getDescription())));

		Optional<ReasonForContactDto> position = interactor.getReason("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		ReasonForContactDto actual = position.get();

		assertEquals(expected.getReason(), actual.getReason());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testGetReasonReturnsEmptyOptionalWhenNoReasonExists() {

		when(repository.findByReason(any())).thenReturn(Optional.empty());

		Optional<ReasonForContactDto> position = interactor.getReason("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteReasonReturnEmptyOptionalWhenNoReasonExists() {

		when(repository.deleteByReason(any())).thenReturn(Optional.empty());

		Optional<ReasonForContactDto> position = interactor.deleteReason("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteReasonReturnReasonWhenReasonExists() {

		ReasonForContactDto expected = new ReasonForContactDto();
		expected.setReason("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.findByReason(any())).thenReturn(Optional.of(new ReasonForContact(expected.getReason(), expected.getDescription())));

		Optional<ReasonForContactDto> position = interactor.getReason("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		ReasonForContactDto actual = position.get();

		assertEquals(expected.getReason(), actual.getReason());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testSaveReasonReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		ReasonForContactDto dto = new ReasonForContactDto();
		dto.setReason("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.saveReason(any())).thenReturn(Optional.empty());

		Optional<ReasonForContactDto> position = interactor.saveReason(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSaveReasonReturnReasonWhenRepositorySaves() {

		ReasonForContactDto expected = new ReasonForContactDto();
		expected.setReason("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.saveReason(any())).thenReturn(Optional.of(new ReasonForContact(expected.getReason(), expected.getDescription())));

		Optional<ReasonForContactDto> position = interactor.saveReason(expected);

		assertTrue(position.isPresent());

		ReasonForContactDto actual = position.get();

		assertEquals(expected.getReason(), actual.getReason());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

}