package com.jabasoft.mms.customermanagement.customer.adapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.address.adapter.CountryConverter;
import com.jabasoft.mms.customermanagement.customer.address.adapter.JpaAddress;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;
import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.JpaEmail;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.JpaPhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.Country;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.PhoneNumber;
import com.jabasoft.mms.customermanagement.customer.spi.CustomerRepository;

@Component
class JpaCustomerDao implements CustomerRepository {

	private SpringJpaCustomerRepository customerRepository;

	@Autowired
	public JpaCustomerDao(SpringJpaCustomerRepository customerRepository) {

		this.customerRepository = customerRepository;
	}

	@Override
	public boolean deleteCustomer(CustomerId customerId) {

		if (!customerRepository.existsByCustomerId(customerId.getCustomerId())) {
			return true;
		}

		customerRepository.deleteById(customerId.getCustomerId());

		return true;
	}

	public Optional<Customer> findCustomer(CustomerId customerId, Customer cos) {

		Optional<JpaCustomer> customer = customerRepository.findById(customerId.getCustomerId());

		Optional<Customer> customer1 = customer.map(this::mapCustomerToDomain);
		System.out.println(customer1.get().equals(cos));
		Optional<Customer> customer2 = customer.map(this::mapCustomerToDomain);

		return customer1;
	}

	@Override
	public Optional<Customer> findCustomer(CustomerId customerId) {

		Optional<JpaCustomer> customer = customerRepository.findById(customerId.getCustomerId());

		return customer.map(this::mapCustomerToDomain);
	}

	@Override
	public Customer saveCustomer(Customer customer) {

		JpaCustomer jpaCustomer = mapCustomerToEntity(customer);
		JpaCustomer savedCustomer = customerRepository.save(jpaCustomer);

		return mapCustomerToDomain(savedCustomer);
	}

	private Customer mapCustomerToDomain(JpaCustomer jpaCustomer) {

		CustomerId customerId = new CustomerId(jpaCustomer.getCustomerId());
		List<ContactPerson> contactPersons = jpaCustomer.getContactPersons().stream()
			.map(this::mapContactPersonToDomain).toList();
		//Address address = mapAddressToDomain(jpaCustomer.getAddress());
		String companyName = jpaCustomer.getCompanyName();

		return new Customer(customerId, companyName, null, contactPersons);
	}

	private Address mapAddressToDomain(JpaAddress address) {

		String city = address.getCity();
		String street = address.getStreet();
		String houseNumber = address.getHouseNumber();
		String zipCode = address.getZipCode();
		Country country = new CountryConverter().convertToEntityAttribute(address.getCountry());

 		return new Address(street, houseNumber, city, country, zipCode);
	}

	private ContactPerson mapContactPersonToDomain(JpaContactPerson jpaContactPerson) {

		BigInteger position = jpaContactPerson.getPosition();
		//ContactPersonPosition contactPersonPosition = new PositionConverter().convertToEntityAttribute(position);

		String firstName = jpaContactPerson.getFirstName();
		String lastName = jpaContactPerson.getLastName();

		List<EMail> emails = jpaContactPerson.getEmails().stream()
			.map(JpaEmail::getEmail)
			.map(EMail::new).toList();

		List<PhoneNumber> phoneNumbers = jpaContactPerson.getPhoneNumbers().stream()
			.map(JpaPhoneNumber::getPhoneNumber)
			.map(PhoneNumber::new).toList();

//		ReasonForContactConverter reasonForContactConverter = new ReasonForContactConverter();
//		List<ReasonForContact> reasonForContacts = jpaContactPerson.getReasonForContacts().stream()
//			.map(JpaReasonForContact::getReasonForContact)
//			.map(reasonForContactConverter::convertToEntityAttribute).toList();

		return new ContactPerson(null, firstName, lastName, emails, phoneNumbers, List.of());
	}

	private JpaCustomer mapCustomerToEntity(Customer customer) {

		JpaCustomer jpaCustomer = new JpaCustomer();

		jpaCustomer.setCompanyName(customer.getCompanyName());

		String customerId = customer.getCustomerId().map(CustomerId::getCustomerId).orElse(UUID.randomUUID().toString());
		jpaCustomer.setCustomerId(customerId);

		List<JpaContactPerson> contactPersons = customer.getContactPersons().stream()
			.map(person -> mapContactPersonToEntity(person, jpaCustomer)).toList();
		jpaCustomer.setContactPersons(contactPersons);

		JpaAddress jpaAddress = mapAddressToEntity(customer.getAddress(), jpaCustomer);
		//jpaCustomer.setAddress(jpaAddress);

		return jpaCustomer;
	}

	private JpaAddress mapAddressToEntity(Address address, JpaCustomer customer){

		JpaAddress jpaAddress = new JpaAddress();
		//jpaAddress.setAddressId(customer);
//		jpaAddress.setCustomer(customer);
		jpaAddress.setCity(address.getCity());
		jpaAddress.setStreet(address.getStreet());
		jpaAddress.setHouseNumber(address.getHouseNumber());
		jpaAddress.setZipCode(address.getZipCode());
		jpaAddress.setCountry(new CountryConverter().convertToDatabaseColumn(address.getCountry()));

		return jpaAddress;
	}

	private JpaContactPerson mapContactPersonToEntity(ContactPerson contactPerson, JpaCustomer jpaCustomer){

		JpaContactPerson jpaContactPerson = new JpaContactPerson();
		//jpaContactPerson.setContactPersonId(jpaCustomer);

//		BigInteger contactPersonPosition = new PositionConverter().convertToDatabaseColumn(contactPerson.getPosition());
//		jpaContactPerson.setPosition(contactPersonPosition);

		jpaContactPerson.setFirstName(contactPerson.getFirstName());
		jpaContactPerson.setLastName(contactPerson.getLastName());

		List<JpaEmail> jpaEmails = new ArrayList<>();
		for (EMail email : contactPerson.getEmails()) {
			JpaEmail jpaEmail = new JpaEmail();
			jpaEmail.setEmail(email.getEmail());
			jpaEmail.setContactPersonId(jpaContactPerson);
			jpaEmails.add(jpaEmail);
		}
		jpaContactPerson.setEmails(jpaEmails);

		List<JpaPhoneNumber> jpaPhoneNumbers = new ArrayList<>();
		for (PhoneNumber phoneNumber : contactPerson.getPhoneNumbers()) {
			JpaPhoneNumber jpaPhoneNumber = new JpaPhoneNumber();
			jpaPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
			jpaPhoneNumber.setContactPersonId(jpaContactPerson);
			jpaPhoneNumbers.add(jpaPhoneNumber);
		}
		jpaContactPerson.setPhoneNumbers(jpaPhoneNumbers);

//		ReasonForContactConverter reasonForContactConverter = new ReasonForContactConverter();
//		List<JpaReasonForContact> jpaReasonForContacts = contactPerson.getReasonsForContact().stream()
//			.map(reasonForContactConverter::convertToDatabaseColumn)
//			.map(reason -> {
//				JpaReasonForContact jpaReasonForContact = new JpaReasonForContact();
//				jpaReasonForContact.setReasonForContact(reason);
//				jpaReasonForContact.setContactPersonId(jpaContactPerson);
//				return jpaReasonForContact;
//			}).toList();
//		jpaContactPerson.setReasonForContacts(jpaReasonForContacts);

		return jpaContactPerson;
	}

}
