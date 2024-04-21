package com.jabasoft.mms.customermanagement.customer.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.address.adapter.CountryConverter;
import com.jabasoft.mms.customermanagement.customer.address.adapter.JpaAddress;
import com.jabasoft.mms.customermanagement.customer.address.adapter.SpringJpaAddressRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPersonDao;
import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.JpaEmail;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.JpaPhoneNumber;
import com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter.JpaContactPersonPosition;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.JpaReasonForContact;
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
import com.jabasoft.mms.customermanagement.customer.spi.CustomerRepository;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

@Component
class JpaCustomerDao implements CustomerRepository {

	private SpringJpaCustomerRepository customerRepository;
	private SpringJpaAddressRepository addressRepository;
	private JpaContactPersonDao contactPersonRepository;

	@Autowired
	public JpaCustomerDao(SpringJpaCustomerRepository customerRepository, SpringJpaAddressRepository addressRepository,
		JpaContactPersonDao contactPersonRepository) {

		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
		this.contactPersonRepository = contactPersonRepository;
	}

	@Override
	public Optional<Customer> deleteCustomer(CustomerId customerId) {

		Optional<JpaCustomer> customer = customerRepository.findById(customerId.getCustomerId());

		if(customer.isPresent()) {
			customerRepository.deleteById(customerId.getCustomerId());
		}

		return customer.map(this::mapCustomerToDomain);
	}

	@Override
	public Optional<Customer> findCustomer(CustomerId customerId) {

		Optional<JpaCustomer> customer = customerRepository.findById(customerId.getCustomerId());

		return customer.map(this::mapCustomerToDomain);
	}

	@Override
	public List<Customer> findAllCustomer(){

		Iterable<JpaCustomer> all = customerRepository.findAll();

		List<Customer> allCustomer = new ArrayList<>();
		all.forEach(jpa -> allCustomer.add(mapCustomerToDomain(jpa)));

		return allCustomer;
	}

	@Override
	public Optional<Customer> saveCustomer(Customer customer) {

		JpaCustomer jpaCustomer = mapCustomerToEntity(customer);
		if (customer.getAddress() != null) {
			addressRepository.save(jpaCustomer.getAddress());
		}
		contactPersonRepository.saveAll(jpaCustomer.getContactPersons());
		JpaCustomer savedCustomer = customerRepository.save(jpaCustomer);

		return Optional.of(mapCustomerToDomain(savedCustomer));
	}

	private Customer mapCustomerToDomain(JpaCustomer jpaCustomer) {

		CustomerId customerId = new CustomerId(jpaCustomer.getCustomerId());
		List<ContactPerson> contactPersons = jpaCustomer.getContactPersons().stream()
			.map(this::mapContactPersonToDomain).toList();
		Address address = mapAddressToDomain(jpaCustomer.getAddress());
		String companyName = jpaCustomer.getCompanyName();

		return new Customer(customerId, companyName, address, contactPersons);
	}

	private Address mapAddressToDomain(JpaAddress address) {

		if (address == null){
			return null;
		}

		String city = address.getCity();
		String street = address.getStreet();
		String houseNumber = address.getHouseNumber();
		String zipCode = address.getZipCode();
		Country country = new CountryConverter().convertToEntityAttribute(address.getCountry());
		AddressId addressId = new AddressId(address.getId());

		return new Address(addressId, street, houseNumber, city, country, zipCode);
	}

	private ContactPerson mapContactPersonToDomain(JpaContactPerson jpaContactPerson) {

		JpaContactPersonPosition jpaPosition = jpaContactPerson.getPosition();
		ContactPersonPosition contactPersonPosition = null;
		if (jpaPosition != null) {
			contactPersonPosition = new ContactPersonPosition(jpaPosition.getPosition(), jpaPosition.getDescription());
		}

		List<ReasonForContact> reasonsForContact = jpaContactPerson.getReasonForContacts().stream()
			.map(reason -> new ReasonForContact(reason.getReason(), reason.getDescription())).toList();

		String firstName = jpaContactPerson.getFirstName();
		String lastName = jpaContactPerson.getLastName();

		List<EMail> emails = jpaContactPerson.getEmails().stream()
			.map(JpaEmail::getEmail)
			.map(EMail::new).toList();

		List<PhoneNumber> phoneNumbers = jpaContactPerson.getPhoneNumbers().stream()
			.map(JpaPhoneNumber::getPhoneNumber)
			.map(PhoneNumber::new).toList();

		ContactPersonId contactPersonId = new ContactPersonId(jpaContactPerson.getContactPersonId());

		return new ContactPerson(contactPersonId, contactPersonPosition, firstName, lastName, emails, phoneNumbers, reasonsForContact);
	}

	private JpaCustomer mapCustomerToEntity(Customer customer) {

		JpaCustomer jpaCustomer = new JpaCustomer();

		jpaCustomer.setCompanyName(customer.getCompanyName());

		Long customerId = customer.getCustomerId().map(CustomerId::getCustomerId).orElse(null);
		jpaCustomer.setCustomerId(customerId);

		List<JpaContactPerson> contactPersons = new ArrayList<>(customer.getContactPersons().stream()
			.map(person -> mapContactPersonToEntity(person)).toList());
		jpaCustomer.setContactPersons(contactPersons);

		JpaAddress jpaAddress = mapAddressToEntity(customer.getAddress());
		jpaCustomer.setAddress(jpaAddress);

		return jpaCustomer;
	}

	private JpaAddress mapAddressToEntity(Address address){

		if (address == null){
			return null;
		}
		JpaAddress jpaAddress = new JpaAddress();
		jpaAddress.setCity(address.getCity());
		jpaAddress.setStreet(address.getStreet());
		jpaAddress.setHouseNumber(address.getHouseNumber());
		jpaAddress.setZipCode(address.getZipCode());
		jpaAddress.setCountry(new CountryConverter().convertToDatabaseColumn(address.getCountry()));
		jpaAddress.setId(address.getAddressId().map(AddressId::getAddressId).orElse(null));

		return jpaAddress;
	}

	private JpaContactPerson mapContactPersonToEntity(ContactPerson contactPerson){

		JpaContactPerson jpaContactPerson = new JpaContactPerson();

		ContactPersonPosition position = contactPerson.getPosition();
		if (position != null && false) {
			JpaContactPersonPosition jpaContactPersonPosition = new JpaContactPersonPosition();
			jpaContactPersonPosition.setPosition(position.getPosition());
			jpaContactPersonPosition.setDescription(position.getDescription());
			jpaContactPerson.setPosition(jpaContactPersonPosition);
		}

		jpaContactPerson.setFirstName(contactPerson.getFirstName());
		jpaContactPerson.setLastName(contactPerson.getLastName());

		List<JpaEmail> jpaEmails = new ArrayList<>();
		for (EMail email : contactPerson.getEmails()) {
			JpaEmail jpaEmail = new JpaEmail();
			jpaEmail.setEmail(email.getEmail());
			jpaEmails.add(jpaEmail);
		}
		jpaContactPerson.setEmails(jpaEmails);

		List<JpaPhoneNumber> jpaPhoneNumbers = new ArrayList<>();
		for (PhoneNumber phoneNumber : contactPerson.getPhoneNumbers()) {
			JpaPhoneNumber jpaPhoneNumber = new JpaPhoneNumber();
			jpaPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
			jpaPhoneNumbers.add(jpaPhoneNumber);
		}
		jpaContactPerson.setPhoneNumbers(jpaPhoneNumbers);

		List<JpaReasonForContact> jpaReasonForContacts = new ArrayList<>(contactPerson.getReasonsForContact().stream()
			.map(reason -> {
				JpaReasonForContact jpaReasonForContact = new JpaReasonForContact();
				jpaReasonForContact.setReason(reason.getReason());
				jpaReasonForContact.setDescription(reason.getDescription());
				return jpaReasonForContact;
			}).toList());
		jpaContactPerson.setReasonForContacts(jpaReasonForContacts);

		jpaContactPerson.setContactPersonId(contactPerson.getContactPersonId().map(ContactPersonId::getContactPersonId).orElse(null));

		return jpaContactPerson;
	}

}
