package com.jabasoft.mms.customermanagement.contactperson.position.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.jabasoft.mms.customermanagement.customer.contactperson.position.spi.ContactPersonPositionRepository;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

class ContactPersonPositionInteractorTest {

	ContactPersonPositionRepository repository;
	ContactPersonPositionInteractor interactor;

	@BeforeEach
	void setUp(){
		repository = mock(repository);
		interactor = new ContactPersonPositionInteractor(repository);
	}

	@Test
	void testGetPositionsWithNoAvailablePositionsReturnsEmptyList() {

		when(repository.findAll()).thenReturn(List.of());

		List<ContactPersonPositionDto> positions = interactor.getPositions();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetPositionsReturnAllPositions(){

		ContactPersonPositionDto dto1 = new ContactPersonPositionDto();
		dto1.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto1.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		ContactPersonPositionDto dto2 = new ContactPersonPositionDto();
		dto1.setPosition("e9285b53-69c5-4dcb-a85b-5369c5ddcbc4");

		List<ContactPersonPositionDto> expected = List.of(dto1, dto2);

		when(repository.findAll()).thenReturn(List.of(
			new ContactPersonPosition(dto1.getPosition(), dto1.getDescription()),
			new ContactPersonPosition(dto2.getPosition(), dto2.getDescription())
		));


		List<ContactPersonPositionDto> actual = interactor.getPositions();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0).getPosition(), actual.get(0).getPosition());
		assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
		assertEquals(expected.get(1).getPosition(), actual.get(1).getPosition());
		assertEquals(expected.get(1).getDescription(), actual.get(1).getDescription());
	}

	@Test
	void testGetPositionReturnsPositionWhenPositionExists() {

		ContactPersonPositionDto expected = new ContactPersonPositionDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.findByPosition(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<ContactPersonPositionDto> position = interactor.getPosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		ContactPersonPositionDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testGetPositionReturnsEmptyOptionalWhenNoPositionExists() {

		when(repository.findByPosition(any())).thenReturn(Optional.empty());

		Optional<ContactPersonPositionDto> position = interactor.getPosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeletePositionReturnEmptyOptionalWhenNoPositionExists() {

		when(repository.deleteByPosition(any())).thenReturn(Optional.empty());

		Optional<ContactPersonPositionDto> position = interactor.deletePosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeletePositionReturnPositionWhenPositionExists() {

		ContactPersonPositionDto expected = new ContactPersonPositionDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.deleteByPosition(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<ContactPersonPositionDto> position = interactor.getPosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		ContactPersonPositionDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testSavePositionReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		ContactPersonPositionDto dto = new ContactPersonPositionDto();
		dto.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.savePosition(any())).thenReturn(Optional.empty());

		Optional<ContactPersonPositionDto> position = interactor.savePosition(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSavePositionReturnPositionWhenRepositorySaves() {

		ContactPersonPositionDto expected = new ContactPersonPositionDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.savePosition(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<ContactPersonPositionDto> position = interactor.savePosition(expected);

		assertTrue(position.isPresent());

		ContactPersonPositionDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

}