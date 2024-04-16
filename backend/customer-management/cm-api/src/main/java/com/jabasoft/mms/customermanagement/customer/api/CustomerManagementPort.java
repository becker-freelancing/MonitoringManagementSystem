package com.jabasoft.mms.customermanagement.customer.api;

import java.util.Optional;

import com.jabasoft.mms.customermanagement.dto.CustomerDto;

public interface CustomerManagementPort {

	public CustomerDto addCustomer(CustomerDto customerDto);

	public boolean deleteCustomer(String customerId);

	public Optional<CustomerDto> getCustomer(String customerId);

}
