package com.jabasoft.mms.customermanagement.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.api.CustomerManagementPort;
import com.jabasoft.mms.customermanagement.api.dto.AddressDto;
import com.jabasoft.mms.customermanagement.api.dto.ContactPersonDto;
import com.jabasoft.mms.customermanagement.api.dto.CustomerDto;
import com.jabasoft.mms.customermanagement.api.dto.ReasonForContactDto;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.PhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.customermanagement.spi.CustomerRepository;

@Component
class CustomerManagementInteractor implements CustomerManagementPort {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerManagementInteractor(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}

	@Override
	public boolean addCustomer(CustomerDto customerDto) {

		Customer customer = mapCustomerToDomain(customerDto);
		return customerRepository.saveCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(String customerId) {

		return customerRepository.deleteCustomer(new CustomerId(customerId));
	}

	@Override
	public CustomerDto getCustomer(String customerId) {

		Optional<Customer> customer = customerRepository.findCustomer(new CustomerId(customerId));

		return mapCustomerToDto(customer);
	}

	private Address mapAddressToDomain(AddressDto addressDto){
		return new Address(addressDto.getStreet(), addressDto.getHouseNumber(), addressDto.getCity(), addressDto.getCountry(),
			addressDto.getZipCode());
	}

	private ContactPerson mapContactPersonToDomain(ContactPersonDto contactPersonDto){

		List<EMail> emails = contactPersonDto.getEmails().stream().map(EMail::new).toList();
		List<PhoneNumber> phoneNumbers = contactPersonDto.getPhoneNumbers().stream().map(PhoneNumber::new).toList();
		List<ReasonForContact> reasonForContacts =
			contactPersonDto.getReasonsForContact().stream().map(reason -> mapEnumByName(ReasonForContact.class, reason))
				.toList();
		return new ContactPerson(contactPersonDto.getPosition(), contactPersonDto.getFirstName(), contactPersonDto.getLastName(), emails, phoneNumbers, reasonForContacts);
	}

	private Customer mapCustomerToDomain(CustomerDto customerDto){

		Address address = mapAddressToDomain(customerDto.getAddress());
		List<ContactPerson> contactPersons =
			customerDto.getContactPersons().stream().map(this::mapContactPersonToDomain).collect(Collectors.toList());
		return new Customer(customerDto.getCompanyName(), address, contactPersons);
	}

	private AddressDto mapAddressToDto(Address address){

		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(addressDto.getStreet());
		addressDto.setHouseNumber(address.getHouseNumber());
		addressDto.setCity(address.getCity());
		addressDto.setCountry(address.getCountry());
		addressDto.setZipCode(address.getZipCode());

		return addressDto;
	}

	private ContactPersonDto mapContactPersonToDto(ContactPerson contactPerson){

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		contactPersonDto.setPosition(contactPerson.getPosition());
		contactPersonDto.setFirstName(contactPerson.getFirstName());
		contactPersonDto.setLastName(contactPersonDto.getLastName());
		contactPersonDto.setEmails(contactPerson.getEmails().stream().map(EMail::getEmail).toList());
		contactPersonDto.setPhoneNumbers(contactPerson.getPhoneNumbers().stream().map(PhoneNumber::getPhoneNumber).toList());
		contactPersonDto.setReasonsForContact(contactPerson.getReasonsForContact().stream().map(reason -> mapEnumByName(
			ReasonForContactDto.class, reason)).toList());

		return contactPersonDto;
	}

	private CustomerDto mapCustomerToDto(Optional<Customer> customer){

		AddressDto address = mapAddressToDto(customer.getAddress());
		List<ContactPersonDto> contactPersons =
			customer.getContactPersons().stream().map(this::mapContactPersonToDto).collect(Collectors.toList());
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCompanyName(customer.getCompanyName());
		customerDto.setAddress(address);
		customerDto.setContactPersons(contactPersons);

		return customerDto;
	}

	private <S extends Enum<S>, T extends Enum<T>> T mapEnumByName(Class<T> targetEnumType, S sourceEnum) {

		if (sourceEnum == null) {
			return null;
		}

		return Enum.valueOf(targetEnumType, sourceEnum.name());
	}

}
