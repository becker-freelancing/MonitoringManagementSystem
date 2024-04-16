package com.jabasoft.mms.customermanagement.customer.spi;

import java.util.Optional;

import com.jabasoft.mms.customermanagement.domain.model.Customer;
import com.jabasoft.mms.customermanagement.domain.model.CustomerId;

public interface CustomerRepository {

	boolean deleteCustomer(CustomerId customerId);
	Optional<Customer> findCustomer(CustomerId customerId);
	public Customer saveCustomer(Customer customer);

}
