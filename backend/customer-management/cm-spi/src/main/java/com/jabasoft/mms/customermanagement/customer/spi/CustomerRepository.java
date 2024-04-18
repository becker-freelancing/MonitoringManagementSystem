package com.jabasoft.mms.customermanagement.customer.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;

public interface CustomerRepository {

	boolean deleteCustomer(CustomerId customerId);
	Optional<Customer> findCustomer(CustomerId customerId);
	List<Customer> findAllCustomer();
	public Customer saveCustomer(Customer customer);

}
