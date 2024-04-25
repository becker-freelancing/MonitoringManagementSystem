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
		repository = mock(CustomerRepository.class);
		interactor = new CustomerManagementInteractor(repository);
	}

	@Test
	void testGetCustomersWithNoAvailableCustomersReturnsEmptyList() {

		when(repository.findAllCustomer()).thenReturn(List.of());

		List<CustomerDto> positions = interactor.findAll();

		assertEquals(0, positions.size());
	}

	@Test
	void testGetCustomersReturnAllCustomers(){

		CustomerDto dto1 = createCustomer1();
		CustomerDto dto2 = createCustomer2();

		List<CustomerDto> expected = List.of(dto1, dto2);

		when(repository.findAllCustomer()).thenReturn(List.of(
			interactor.mapCustomerToDomain(dto1),
			interactor.mapCustomerToDomain(dto2)
		));


		List<CustomerDto> actual = interactor.findAll();

		assertEquals(expected.size(), actual.size());
		assertEquals(expected.get(0), actual.get(0));
		assertEquals(expected.get(1), actual.get(1));
	}

	@Test
	void testGetCustomerReturnsCustomerWhenCustomerExists() {

		CustomerDto expected = createCustomer1();

		when(repository.findCustomer(any())).thenReturn(Optional.of(interactor.mapCustomerToDomain(expected)));

		Optional<CustomerDto> position = interactor.getCustomer(1234L);

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testGetCustomerReturnsEmptyOptionalWhenNoCustomerExists() {

		when(repository.findCustomer(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.getCustomer(1234L);

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteCustomerReturnEmptyOptionalWhenNoCustomerExists() {

		when(repository.findCustomer(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.deleteCustomer(1234L);

		assertTrue(position.isEmpty());
	}

	@Test
	void testDeleteCustomerReturnCustomerWhenCustomerExists() {

		CustomerDto expected = createCustomer1();

		when(repository.deleteCustomer(any())).thenReturn(Optional.of(interactor.mapCustomerToDomain(expected)));

		Optional<CustomerDto> position = interactor.deleteCustomer(1234L);

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected, actual);
	}

	@Test
	void testSaveCustomerReturnsEmptyOptionalWhenRepositoryDoesNotSave() {

		CustomerDto dto = createCustomer1();

		when(repository.saveCustomer(any())).thenReturn(Optional.empty());

		Optional<CustomerDto> position = interactor.saveCustomer(dto);

		assertTrue(position.isEmpty());
	}

	@Test
	void testSaveCustomerReturnCustomerWhenRepositorySaves() {

		CustomerDto expected = createCustomer1();

		when(repository.saveCustomer(any())).thenReturn(Optional.of(interactor.mapCustomerToDomain(expected)));

		Optional<CustomerDto> position = interactor.saveCustomer(expected);

		assertTrue(position.isPresent());

		CustomerDto actual = position.get();

		assertEquals(expected, actual);
	}

	private static CustomerDto createCustomer1(){
		CustomerDto customer = new CustomerDto();
		customer.setCompanyName("658d0649-d5e5-4a1e-8d06-49d5e57a1edb");

		AddressDto addressDto = new AddressDto();
		addressDto.setCity("fd0daefc-00d4-4cc0-8dae-fc00d49cc0e2");
		addressDto.setHouseNumber("e4367491-5280-4d9b-b674-9152809d9b6f");
		addressDto.setCity("5b3f2a6c-ae18-4109-bf2a-6cae182109fd");
		addressDto.setCountry(CountryDto.SPAIN.getCountryName());
		addressDto.setZipCode("10666a5c-aae0-466f-a66a-5caae0066f2a");
		customer.setAddress(addressDto);

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		ContactPersonPositionDto positionDto = new ContactPersonPositionDto();
		positionDto.setPosition("0d901bf1-5d87-4d75-901b-f15d872d7548");
		positionDto.setDescription("b0d2b249-d255-4470-92b2-49d255547050");
		contactPersonDto.setPosition(positionDto);
		contactPersonDto.setFirstName("29313c3e-9977-4fcb-b13c-3e99779fcb8d");
		contactPersonDto.setLastName("14d3abd0-4a6d-4e54-93ab-d04a6d2e54c6");
		contactPersonDto.setEmail("2de373a8-0e87-439d-a373-a80e87f39dfe");
		contactPersonDto.setPhoneNumber("adcc727d-89a2-43bd-8c72-7d89a213bdfa");
		ReasonForContactDto reasonForContactDto = new ReasonForContactDto();
		reasonForContactDto.setReason("776fe624-3a55-41fb-afe6-243a55c1fb3b");
		reasonForContactDto.setDescription("032fcfd1-54d0-4897-afcf-d154d0589737");
		contactPersonDto.setReasonForContact(reasonForContactDto);
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
		addressDto.setCountry(CountryDto.GERMANY.getCountryName());
		addressDto.setZipCode("6925e02f-86be-4ff3-a5e0-2f86be3ff3d6");
		customer.setAddress(addressDto);

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		ContactPersonPositionDto positionDto = new ContactPersonPositionDto();
		positionDto.setPosition("4d8ec752-d3fa-4879-8ec7-52d3fa087951");
		positionDto.setDescription("d94ed78e-fa25-4006-8ed7-8efa25200632");
		contactPersonDto.setPosition(positionDto);
		contactPersonDto.setFirstName("f34f3070-b670-49d4-8f30-70b670f9d40e");
		contactPersonDto.setLastName("6e66e13c-be34-4d9c-a6e1-3cbe344d9c516");
		contactPersonDto.setEmail("b3e36c95-82b5-4786-a36c-9582b5c786df");
		contactPersonDto.setPhoneNumber("610893fe-3d8d-406f-8893-fe3d8de06f68");
		ReasonForContactDto reasonForContactDto = new ReasonForContactDto();
		reasonForContactDto.setReason("a3ae4926-a478-4386-ae49-26a4788386eb");
		reasonForContactDto.setDescription("da420f91-72aa-48aa-820f-9172aa08aa60");
		contactPersonDto.setReasonForContact(reasonForContactDto);
		customer.setContactPersons(List.of(contactPersonDto));

		return customer;
	}
}