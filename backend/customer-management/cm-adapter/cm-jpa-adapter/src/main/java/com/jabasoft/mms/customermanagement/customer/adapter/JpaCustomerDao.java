package com.jabasoft.mms.customermanagement.customer.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.address.adapter.CountryConverter;
import com.jabasoft.mms.customermanagement.customer.address.adapter.JpaAddress;
import com.jabasoft.mms.customermanagement.customer.address.adapter.SpringJpaAddressRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPersonDao;
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

		JpaReasonForContact jpaReasonForContact = jpaContactPerson.getReasonForContacts();
		ReasonForContact reasonForContact = null;
		if (jpaReasonForContact != null) {
			reasonForContact = new ReasonForContact(jpaReasonForContact.getReason(), jpaReasonForContact.getDescription());
		}

		String firstName = jpaContactPerson.getFirstName();
		String lastName = jpaContactPerson.getLastName();

		EMail email = new EMail(jpaContactPerson.getEmail());
		PhoneNumber phoneNumber = new PhoneNumber(jpaContactPerson.getPhoneNumber());

		ContactPersonId contactPersonId = new ContactPersonId(jpaContactPerson.getContactPersonId());

		return new ContactPerson(contactPersonId, contactPersonPosition, firstName, lastName, email, phoneNumber, reasonForContact);
	}

	private JpaCustomer mapCustomerToEntity(Customer customer) {

		JpaCustomer jpaCustomer = new JpaCustomer();

		jpaCustomer.setCompanyName(customer.getCompanyName());

		Long customerId = customer.getCustomerId().map(CustomerId::getCustomerId).orElse(null);
		jpaCustomer.setCustomerId(customerId);

		List<JpaContactPerson> contactPersons = new ArrayList<>(customer.getContactPersons().stream()
			.map(this::mapContactPersonToEntity).toList());
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
		if (position != null) {
			JpaContactPersonPosition jpaContactPersonPosition = new JpaContactPersonPosition();
			jpaContactPersonPosition.setPosition(position.getPosition());
			jpaContactPersonPosition.setDescription(position.getDescription());
			jpaContactPerson.setPosition(jpaContactPersonPosition);
		}

		jpaContactPerson.setFirstName(contactPerson.getFirstName());
		jpaContactPerson.setLastName(contactPerson.getLastName());

		jpaContactPerson.setEmail(Optional.ofNullable(contactPerson.getEmail()).map(EMail::getEmail).orElse(null));
		jpaContactPerson.setPhoneNumbers(Optional.ofNullable(contactPerson.getPhoneNumber()).map(PhoneNumber::getPhoneNumber).orElse(null));

		JpaReasonForContact jpaReasonForContact = new JpaReasonForContact();
		jpaReasonForContact.setReason(Optional.ofNullable(contactPerson.getReasonForContact()).map(ReasonForContact::getReason).orElse(null));
		jpaReasonForContact.setDescription(Optional.ofNullable(contactPerson.getReasonForContact()).map(ReasonForContact::getDescription).orElse(null));
		jpaContactPerson.setReasonForContact(jpaReasonForContact);

		jpaContactPerson.setContactPersonId(contactPerson.getContactPersonId().map(ContactPersonId::getContactPersonId).orElse(null));

		return jpaContactPerson;
	}

}
