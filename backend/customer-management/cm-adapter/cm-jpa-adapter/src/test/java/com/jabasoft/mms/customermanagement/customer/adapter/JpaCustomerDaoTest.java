package com.jabasoft.mms.customermanagement.customer.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.customermanagement.MmsDaoImplTest;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.Country;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.PhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.junit.beans.RandomBeanCreator;

@MmsDaoImplTest
class JpaCustomerDaoTest {

	private JpaCustomerDao customerDao;
	private RandomBeanCreator<Customer> customerCreator;

	@Autowired
	public JpaCustomerDaoTest(JpaCustomerDao customerDao) {

		this.customerDao = customerDao;
	}

	@BeforeEach
	void setUp(){

		customerCreator = new RandomBeanCreator<>() {

			@Override
			protected Class<? extends Customer> getBeanClass() {

				return Customer.class;
			}

		};
	}

	@Test
	void testSaveAndLoadCustomers(){

		List<Customer> customers = customerCreator.createBeans().toList();//List.of(createCustomer(), createCustomer());//

		List<Customer> expectedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			Customer savedCustomer = customerDao.saveCustomer(customer);
			expectedCustomers.add(savedCustomer);
		}

		for (Customer expected : expectedCustomers) {
			Optional<Customer> actual = customerDao.findCustomer(expected.getCustomerId().orElse(null), expected);

			assertTrue(actual.isPresent());
			assertEquals(expected, actual.get());
		}

	}

	private Customer createCustomer(){
		Country country = Math.random() < 0.5 ? Country.GERMANY : Country.SPAIN;
		return new Customer("Test GmbH", new Address("Hauptstr.", "48A", "Test", country, "12345"), List.of(createContactPerson1()));
	}

	private ContactPerson createContactPerson1(){
		return new ContactPerson(ContactPersonPosition.CEO, "John", "Doe", createEmails(), createPhoneNumber(), createReasonForContact());
	}

	private List<EMail> createEmails(){
		return List.of(new EMail("abc@test.de"), new EMail("xy@test.com"));
	}

	private List<PhoneNumber> createPhoneNumber(){
		return List.of(new PhoneNumber("123445564"), new PhoneNumber("+49 29738273-2873"));
	}

	private List<ReasonForContact> createReasonForContact(){
		return List.of(ReasonForContact.HUMAN_RESOURCES, ReasonForContact.MARKETING);
	}

}