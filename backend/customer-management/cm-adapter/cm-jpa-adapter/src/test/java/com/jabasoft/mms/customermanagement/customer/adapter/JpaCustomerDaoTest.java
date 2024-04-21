package com.jabasoft.mms.customermanagement.customer.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.customermanagement.MmsDaoImplTest;
import com.jabasoft.mms.customermanagement.domain.model.Address;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.Country;
import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;
import com.jabasoft.mms.customermanagement.domain.model.EMail;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.junit.beans.RandomBeanSupplier;
import com.jabasoft.mms.junit.beans.RandomBeanSupplierRegistrar;
import com.jabasoft.mms.junit.beans.RandomBeanSupplierRegistry;

@MmsDaoImplTest
class JpaCustomerDaoTest {

	private JpaCustomerDao customerDao;
	private RandomBeanSupplier<Customer> customerCreator;

	@Autowired
	public JpaCustomerDaoTest(JpaCustomerDao customerDao) {

		this.customerDao = customerDao;
	}

	@BeforeAll
	static void registerBeanSupplier() throws NoSuchMethodException {

		RandomBeanSupplierRegistry.clear();

		new RandomBeanSupplierRegistrar<>(Customer.class)
			.withConstructors(List.of(Customer.class.getConstructor(String.class, Address.class, List.class))).register();

		new RandomBeanSupplierRegistrar<>(Address.class)
			.withConstructors(List.of(Address.class.getConstructor(
				String.class,
				String.class,
				String.class,
				Country.class,
				String.class))).register();

		new RandomBeanSupplierRegistrar<>(ContactPerson.class)
			.withConstructors(List.of(ContactPerson.class.getConstructor(
				ContactPersonPosition.class,
				String.class,
				String.class,
				List.class,
				List.class,
				List.class))).register();

		new RandomBeanSupplierRegistrar<>(ContactPersonPosition.class)
			.withConstructors(List.of(ContactPersonPosition.class.getConstructor(String.class, String.class))).register();
	}

	@BeforeEach
	void setUp() {

		customerCreator = RandomBeanSupplierRegistry.getRandomBeanSupplier(Customer.class);
	}

	@Test
	void testSaveAndLoadCustomers() {

		List<Customer> customers = customerCreator.createBeans(50);

		List<Customer> expectedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			Optional<Customer> savedCustomer = customerDao.saveCustomer(customer);
			expectedCustomers.add(savedCustomer.orElseThrow(AssertionFailedError::new));
		}

		for (Customer expected : expectedCustomers) {
			Optional<Customer> actual = customerDao.findCustomer(expected.getCustomerId().orElse(null));

			assertTrue(actual.isPresent());
			assertEquals(expected, actual.get());
			assertTrue(customers.contains(actual.get()));
		}
	}

	@Test
	void testDeleteCustomerWithExistingCustomerReturnsTrue() {

		List<Customer> customers = customerCreator.createBeans(3);

		List<Customer> expectedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			Optional<Customer> savedCustomer = customerDao.saveCustomer(customer);
			expectedCustomers.add(savedCustomer.orElseThrow(AssertionFailedError::new));
		}

		Customer deleted = expectedCustomers.remove(0);

		assertEquals(3, customerDao.findAllCustomer().size());

		Optional<Customer> isDeleted = customerDao.deleteCustomer(deleted.getCustomerId().get());
		List<Customer> allCustomerAfterDelete = customerDao.findAllCustomer();

		assertTrue(isDeleted.isPresent());
		assertEquals(2, allCustomerAfterDelete.size());
		assertFalse(allCustomerAfterDelete.contains(deleted));
		assertEquals(deleted, isDeleted.get());
	}

	@Test
	void testDeleteCustomerWithNoExistingCustomerReturnFalse() {

		List<Customer> customers = customerCreator.createBeans(3);

		for (Customer customer : customers) {
			customerDao.saveCustomer(customer);
		}

		Optional<Customer> isDeleted = customerDao.deleteCustomer(new CustomerId(Long.MAX_VALUE));

		assertTrue(isDeleted.isEmpty());
	}

	@Test
	void addingAContactPersonAddsNewContactPerson() {

		List<Customer> beans = customerCreator.createBeans(1);
		assertEquals(1, beans.size());

		Customer customer = beans.get(0);

		Customer expectedCustomer = customerDao.saveCustomer(customer).orElseThrow(AssertionFailedError::new);

		assertEquals(customer, expectedCustomer);

		ContactPerson contactPerson = new ContactPerson(
			new ContactPersonPosition("CEO"),
			"First",
			"Last",
			List.of(new EMail(UUID.randomUUID().toString()), new EMail(UUID.randomUUID().toString())),
			List.of(),
			List.of(new ReasonForContact("Marketing")));

		expectedCustomer.addContactPerson(contactPerson);

		Customer actualCustomer = customerDao.saveCustomer(expectedCustomer).orElseThrow(AssertionFailedError::new);

		assertEquals(expectedCustomer, actualCustomer);
	}

}