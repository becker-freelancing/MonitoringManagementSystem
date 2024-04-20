package com.jabasoft.mms.customermanagement.customer.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.api.CustomerManagementPort;
import com.jabasoft.mms.customermanagement.domain.model.AddressId;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonId;
import com.jabasoft.mms.customermanagement.dto.AddressDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;
import com.jabasoft.mms.customermanagement.dto.CountryDto;
import com.jabasoft.mms.customermanagement.dto.CustomerDto;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.Country;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.PhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.customermanagement.customer.spi.CustomerRepository;

@Component
class CustomerManagementInteractor implements CustomerManagementPort {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerManagementInteractor(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {

		Customer customer = mapCustomerToDomain(customerDto);
		Customer savedCustomer = customerRepository.saveCustomer(customer);

		return mapCustomerToDto(savedCustomer);
	}

	@Override
	public Optional<CustomerDto> deleteCustomer(Long customerId) {

		Optional<Customer> customer = customerRepository.deleteCustomer(new CustomerId(customerId));

		return customer.map(this::mapCustomerToDto);
	}

	@Override
	public Optional<CustomerDto> getCustomer(Long customerId) {

		Optional<Customer> customer = customerRepository.findCustomer(new CustomerId(customerId));

		return customer.map(this::mapCustomerToDto);
	}

	@Override
	public List<CustomerDto> findAll() {

		List<Customer> customers = customerRepository.findAllCustomer();

		return customers.stream().map(this::mapCustomerToDto).toList();
	}

	private Address mapAddressToDomain(AddressDto addressDto){

		String street = addressDto.getStreet();
		String houseNumber = addressDto.getHouseNumber();
		String city = addressDto.getCity();
		Country country = mapEnumByName(Country.class, addressDto.getCountry());
		String zipCode = addressDto.getZipCode();
		Long addressId = addressDto.getId();

		if(addressId != null){
			return new Address(new AddressId(addressId), street, houseNumber, city, country, zipCode);
		}
		return new Address(street, houseNumber, city, country, zipCode);
	}

	private ContactPerson mapContactPersonToDomain(ContactPersonDto contactPersonDto){

		List<EMail> emails = contactPersonDto.getEmails().stream().map(EMail::new).toList();
		List<PhoneNumber> phoneNumbers = contactPersonDto.getPhoneNumbers().stream().map(PhoneNumber::new).toList();
		List<ReasonForContact> reasonForContacts = contactPersonDto.getReasonsForContact().stream()
			.map(reason -> new ReasonForContact(reason.getReason(), reason.getDescription()))
			.toList();
		ContactPersonPosition contactPersonPosition = new ContactPersonPosition(
			contactPersonDto.getPosition().getPosition(),
			contactPersonDto.getPosition().getDescription());

		Long id = contactPersonDto.getId();

		if(id != null){
			return new ContactPerson(new ContactPersonId(id), contactPersonPosition, contactPersonDto.getFirstName(), contactPersonDto.getLastName(), emails, phoneNumbers, reasonForContacts);
		}
		return new ContactPerson(contactPersonPosition, contactPersonDto.getFirstName(), contactPersonDto.getLastName(), emails, phoneNumbers, reasonForContacts);
	}

	private Customer mapCustomerToDomain(CustomerDto customerDto){

		Address address = mapAddressToDomain(customerDto.getAddress());
		List<ContactPerson> contactPersons =
			customerDto.getContactPersons().stream().map(this::mapContactPersonToDomain).collect(Collectors.toList());
		Long id = customerDto.getId();
		if(id != null){
			return new Customer(new CustomerId(id), customerDto.getCompanyName(), address, contactPersons);
		}
		return new Customer(customerDto.getCompanyName(), address, contactPersons);
	}

	private AddressDto mapAddressToDto(Address address){

		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(addressDto.getStreet());
		addressDto.setHouseNumber(address.getHouseNumber());
		addressDto.setCity(address.getCity());
		addressDto.setCountry(mapEnumByName(CountryDto.class, address.getCountry()));
		addressDto.setZipCode(address.getZipCode());
		addressDto.setId(address.getAddressId().map(AddressId::getAddressId).orElse(null));

		return addressDto;
	}

	private ContactPersonDto mapContactPersonToDto(ContactPerson contactPerson){

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		ContactPersonPositionDto position = new ContactPersonPositionDto();
		position.setPosition(contactPerson.getPosition().getPosition());
		position.setDescription(contactPerson.getPosition().getDescription());
		contactPersonDto.setPosition(position);
		contactPersonDto.setFirstName(contactPerson.getFirstName());
		contactPersonDto.setLastName(contactPersonDto.getLastName());
		contactPersonDto.setEmails(contactPerson.getEmails().stream().map(EMail::getEmail).toList());
		contactPersonDto.setPhoneNumbers(contactPerson.getPhoneNumbers().stream().map(PhoneNumber::getPhoneNumber).toList());
		contactPersonDto.setReasonsForContact(contactPerson.getReasonsForContact().stream()
			.map(reason -> {
			ReasonForContactDto reasonForCantact = new ReasonForContactDto();
			reasonForCantact.setReason(reason.getReason());
			reasonForCantact.setDescription(reason.getDescription());
			return reasonForCantact;
		}).toList());
		contactPersonDto.setId(contactPerson.getContactPersonId().map(ContactPersonId::getContactPersonId).orElse(null));

		return contactPersonDto;
	}

	private CustomerDto mapCustomerToDto(Customer customer){

		AddressDto address = mapAddressToDto(customer.getAddress());
		List<ContactPersonDto> contactPersons =
			customer.getContactPersons().stream().map(this::mapContactPersonToDto).collect(Collectors.toList());
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCompanyName(customer.getCompanyName());
		customerDto.setAddress(address);
		customerDto.setContactPersons(contactPersons);
		customerDto.setId(customer.getCustomerId().map(CustomerId::getCustomerId).orElse(null));

		return customerDto;
	}

	private <S extends Enum<S>, T extends Enum<T>> T mapEnumByName(Class<T> targetEnumType, S sourceEnum) {

		if (sourceEnum == null) {
			return null;
		}

		return Enum.valueOf(targetEnumType, sourceEnum.name());
	}

}
