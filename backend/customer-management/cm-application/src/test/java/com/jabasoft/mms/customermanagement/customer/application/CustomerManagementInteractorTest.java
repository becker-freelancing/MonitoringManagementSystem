package com.jabasoft.mms.customermanagement.customer.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jabasoft.mms.customermanagement.customer.spi.CustomerRepository;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.dto.AddressDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;
import com.jabasoft.mms.customermanagement.dto.CountryDto;
import com.jabasoft.mms.customermanagement.dto.CustomerDto;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

class CustomerManagementInteractorTest {

	CustomerRepository repository;
	CustomerManagementInteractor interactor;

	@BeforeEach
	void setUp(){
		repository = mock(repository);
		interactor = new CustomerManagementInteractor(repository);
	}

	@Test
	void testGetPositionsWithNoAvailablePositionsReturnsEmptyList() {

		when(repository.findAllCustomer()).thenReturn(List.of());

		List<CustomerDto> positions = interactor.findAll();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetPositionsReturnAllPositions(){

		CustomerDto dto1 = new CustomerDto();
		dto1.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto1.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		CustomerDto dto2 = new CustomerDto();
		dto1.setPosition("e9285b53-69c5-4dcb-a85b-5369c5ddcbc4");

		List<CustomerDto> expected = List.of(dto1, dto2);

		when(repository.findAllCustomer()).thenReturn(List.of(
			new ContactPersonPosition(dto1.getPosition(), dto1.getDescription()),
			new ContactPersonPosition(dto2.getPosition(), dto2.getDescription())
		));


		List<CustomerDto> actual = interactor.findAll();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0).getPosition(), actual.get(0).getPosition());
		assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
		assertEquals(expected.get(1).getPosition(), actual.get(1).getPosition());
		assertEquals(expected.get(1).getDescription(), actual.get(1).getDescription());
	}

	@Test
	void testGetPositionReturnsPositionWhenPositionExists() {

		CustomerDto expected = new CustomerDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.findCustomer(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<CustomerDto> position = interactor.getCustomer("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testGetPositionReturnsEmptyOptionalWhenNoPositionExists() {

		when(repository.findCustomer(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.getPosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeletePositionReturnEmptyOptionalWhenNoPositionExists() {

		when(repository.findCustomer(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.deletePosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeletePositionReturnPositionWhenPositionExists() {

		CustomerDto expected = new CustomerDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.deleteCustomer(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<CustomerDto> position = interactor.getPosition("600b3ebd-5ee1-484c-8b3e-bd5ee1584c36");

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	@Test
	void testSavePositionReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		CustomerDto dto = new CustomerDto();
		dto.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		dto.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.savePosition(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.savePosition(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSavePositionReturnPositionWhenRepositorySaves() {

		CustomerDto expected = new CustomerDto();
		expected.setPosition("7feb44d1-85a5-438e-ab44-d185a5538e22");
		expected.setDescription("fb4d339c-fd9b-4e65-8d33-9cfd9b6e659d");

		when(repository.savePosition(any())).thenReturn(Optional.of(new ContactPersonPosition(expected.getPosition(), expected.getDescription())));

		Optional<CustomerDto> position = interactor.savePosition(expected);

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected.getPosition(), actual.getPosition());
		assertEquals(expected.getDescription(), actual.getDescription());
	}

	private static CustomerDto createCustomer1(){
		CustomerDto customer = new CustomerDto();
		customer.setCompanyName("658d0649-d5e5-4a1e-8d06-49d5e57a1edb");

		AddressDto addressDto = new AddressDto();
		addressDto.setCity("fd0daefc-00d4-4cc0-8dae-fc00d49cc0e2");
		addressDto.setHouseNumber("e4367491-5280-4d9b-b674-9152809d9b6f");
		addressDto.setCity("5b3f2a6c-ae18-4109-bf2a-6cae182109fd");
		addressDto.setCountry(CountryDto.SPAIN);
		addressDto.setZipCode("10666a5c-aae0-466f-a66a-5caae0066f2a");
		customer.setAddress(addressDto);

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		ContactPersonPositionDto positionDto = new ContactPersonPositionDto();
		positionDto.setPosition("0d901bf1-5d87-4d75-901b-f15d872d7548");
		positionDto.setDescription("b0d2b249-d255-4470-92b2-49d255547050");
		contactPersonDto.setPosition(positionDto);
		contactPersonDto.setFirstName("29313c3e-9977-4fcb-b13c-3e99779fcb8d");
		contactPersonDto.setLastName("14d3abd0-4a6d-4e54-93ab-d04a6d2e54c6");
		contactPersonDto.setEmails(List.of("2de373a8-0e87-439d-a373-a80e87f39dfe", "e56075c8-df39-413d-a075-c8df39f13d6b"));
		contactPersonDto.setPhoneNumbers(List.of("adcc727d-89a2-43bd-8c72-7d89a213bdfa", "7e641f33-01c7-495e-a41f-3301c7595ef8"));
		ReasonForContactDto reasonForContactDto = new ReasonForContactDto();
		reasonForContactDto.setReason("776fe624-3a55-41fb-afe6-243a55c1fb3b");
		reasonForContactDto.setDescription("032fcfd1-54d0-4897-afcf-d154d0589737");
		contactPersonDto.setReasonsForContact(List.of(reasonForContactDto));
		customer.setContactPersons(List.of(contactPersonDto));

		return customer;
	}

	private static CustomerDto createCustomer2(){
		CustomerDto customer = new CustomerDto();
		customer.setCompanyName("53a7f378-2ad4-46dd-a7f3-782ad496dd1e");

		AddressDto addressDto = new AddressDto();
		addressDto.setCity("c27466b1-5a46-4c88-b466-b15a468c88ba");
		addressDto.setHouseNumber("3c313150-fb66-4f98-b131-50fb66af98d6");
		addressDto.setCity("4d55fd84-0589-401e-95fd-840589001e85");
		addressDto.setCountry(CountryDto.GERMANY);
		addressDto.setZipCode("6925e02f-86be-4ff3-a5e0-2f86be3ff3d6");
		customer.setAddress(addressDto);

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		ContactPersonPositionDto positionDto = new ContactPersonPositionDto();
		positionDto.setPosition("4d8ec752-d3fa-4879-8ec7-52d3fa087951");
		positionDto.setDescription("d94ed78e-fa25-4006-8ed7-8efa25200632");
		contactPersonDto.setPosition(positionDto);
		contactPersonDto.setFirstName("f34f3070-b670-49d4-8f30-70b670f9d40e");
		contactPersonDto.setLastName("6e66e13c-be34-4d9c-a6e1-3cbe344d9c516");
		contactPersonDto.setEmails(List.of("b3e36c95-82b5-4786-a36c-9582b5c786df", "831f2d98-61de-415d-9f2d-9861de115d86b"));
		contactPersonDto.setPhoneNumbers(List.of("69087b6f-3865-4c23-887b-6f38653c234c", "610893fe-3d8d-406f-8893-fe3d8de06f68"));
		ReasonForContactDto reasonForContactDto = new ReasonForContactDto();
		reasonForContactDto.setReason("a3ae4926-a478-4386-ae49-26a4788386eb");
		reasonForContactDto.setDescription("da420f91-72aa-48aa-820f-9172aa08aa60");
		contactPersonDto.setReasonsForContact(List.of(reasonForContactDto));
		customer.setContactPersons(List.of(contactPersonDto));

		return customer;
	}
}