package com.jabasoft.mms.customermanagement.customer.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.api.CustomerManagementPort;
import com.jabasoft.mms.customermanagement.customer.spi.CustomerRepository;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.AddressId;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonId;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.Country;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.PhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.customermanagement.dto.AddressDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonDto;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;
import com.jabasoft.mms.customermanagement.dto.CountryDto;
import com.jabasoft.mms.customermanagement.dto.CustomerDto;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

@Component
class CustomerManagementInteractor implements CustomerManagementPort {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerManagementInteractor(CustomerRepository customerRepository) {

		this.customerRepository = customerRepository;
	}

	@Override
	public Optional<CustomerDto> saveCustomer(CustomerDto customerDto) {

		Customer customer = mapCustomerToDomain(customerDto);
		Optional<Customer> savedCustomer = customerRepository.saveCustomer(customer);

		return savedCustomer.map(this::mapCustomerToDto);
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

	private Address mapAddressToDomain(AddressDto addressDto) {

		if (addressDto == null) {
			return null;
		}

		String street = addressDto.getStreet();
		String houseNumber = addressDto.getHouseNumber();
		String city = addressDto.getCity();
		Country country = mapEnumByName(Country.class, CountryDto.fromCountryName(addressDto.getCountry()));
		String zipCode = addressDto.getZipCode();
		Long addressId = addressDto.getAddressId();

		if (addressId != null) {
			return new Address(new AddressId(addressId), street, houseNumber, city, country, zipCode);
		}
		return new Address(street, houseNumber, city, country, zipCode);
	}

	private ContactPerson mapContactPersonToDomain(ContactPersonDto contactPersonDto) {

		if (contactPersonDto == null) {
			return null;
		}

		EMail eMail = Optional.ofNullable(contactPersonDto.getEmail()).map(EMail::new).orElse(null);
		PhoneNumber phoneNumber = Optional.ofNullable(contactPersonDto.getPhoneNumber()).map(PhoneNumber::new).orElse(null);
		ReasonForContact reasonForContact = Optional.ofNullable(contactPersonDto.getReasonForContact()).map(reason -> new ReasonForContact(reason.getReason(), reason.getDescription())).orElse(null);
		ContactPersonPosition contactPersonPosition = Optional.ofNullable(contactPersonDto.getPosition()).map(position -> new ContactPersonPosition(position.getPosition(), position.getDescription())).orElse(null);

		Long id = contactPersonDto.getId();

		if (id != null) {
			return new ContactPerson(
				new ContactPersonId(id),
				contactPersonPosition,
				contactPersonDto.getFirstName(),
				contactPersonDto.getLastName(),
				eMail,
				phoneNumber,
				reasonForContact);
		}
		return new ContactPerson(
			contactPersonPosition,
			contactPersonDto.getFirstName(),
			contactPersonDto.getLastName(),
			eMail,
			phoneNumber,
			reasonForContact);
	}

	protected Customer mapCustomerToDomain(CustomerDto customerDto) {

		Address address = mapAddressToDomain(customerDto.getAddress());
		List<ContactPerson> contactPersons =
			Optional.ofNullable(customerDto.getContactPersons()).orElse(List.of()).stream().map(this::mapContactPersonToDomain)
				.filter(Objects::nonNull).collect(Collectors.toList());
		Long id = customerDto.getCustomerId();
		if (id != null) {
			return new Customer(new CustomerId(id), customerDto.getCompanyName(), address, contactPersons);
		}
		return new Customer(customerDto.getCompanyName(), address, contactPersons);
	}

	private AddressDto mapAddressToDto(Address address) {

		if (address == null) {
			return null;
		}

		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(address.getStreet());
		addressDto.setHouseNumber(address.getHouseNumber());
		addressDto.setCity(address.getCity());
		CountryDto countryDto = mapEnumByName(CountryDto.class, address.getCountry());
		addressDto.setCountry(countryDto == null ? null : countryDto.getCountryName());
		addressDto.setZipCode(address.getZipCode());
		addressDto.setAddressId(address.getAddressId().map(AddressId::getAddressId).orElse(null));

		return addressDto;
	}

	private ContactPersonDto mapContactPersonToDto(ContactPerson contactPerson) {

		if (contactPerson == null){
			return null;
		}

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		contactPersonDto.setFirstName(contactPerson.getFirstName());
		contactPersonDto.setLastName(contactPerson.getLastName());
		contactPersonDto.setEmail(Optional.ofNullable(contactPerson.getEmail()).map(EMail::getEmail).orElse(null));
		contactPersonDto.setPhoneNumber(Optional.ofNullable(contactPerson.getPhoneNumber()).map(PhoneNumber::getPhoneNumber).orElse(null));
		contactPersonDto.setReasonForContact(Optional.ofNullable(contactPerson.getReasonForContact()).map(reason -> {
			ReasonForContactDto reasonForContactDto = new ReasonForContactDto();
			reasonForContactDto.setReason(reason.getReason());
			reasonForContactDto.setDescription(reason.getDescription());
			return reasonForContactDto;
		}).orElse(null));
		contactPersonDto.setPosition(Optional.ofNullable(contactPerson.getPosition()).map(position -> {
			ContactPersonPositionDto contactPersonPositionDto = new ContactPersonPositionDto();
			contactPersonPositionDto.setPosition(position.getPosition());
			contactPersonPositionDto.setDescription(position.getDescription());
			return contactPersonPositionDto;
		}).orElse(null));
		contactPersonDto.setId(contactPerson.getContactPersonId().map(ContactPersonId::getContactPersonId).orElse(null));

		return contactPersonDto;
	}

	private CustomerDto mapCustomerToDto(Customer customer) {

		AddressDto address = mapAddressToDto(customer.getAddress());
		List<ContactPersonDto> contactPersons =
			customer.getContactPersons().stream().map(this::mapContactPersonToDto).collect(Collectors.toList());
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCompanyName(customer.getCompanyName());
		customerDto.setAddress(address);
		customerDto.setContactPersons(contactPersons);
		customerDto.setCustomerId(customer.getCustomerId().map(CustomerId::getCustomerId).orElse(null));

		return customerDto;
	}

	private <S extends Enum<S>, T extends Enum<T>> T mapEnumByName(Class<T> targetEnumType, S sourceEnum) {

		if (sourceEnum == null) {
			return null;
		}

		return Enum.valueOf(targetEnumType, sourceEnum.name());
	}

}
