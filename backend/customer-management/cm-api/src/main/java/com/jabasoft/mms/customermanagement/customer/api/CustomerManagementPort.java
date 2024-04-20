package com.jabasoft.mms.customermanagement.customer.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.dto.CustomerDto;

public interface CustomerManagementPort {

	public CustomerDto saveCustomer(CustomerDto customerDto);

	public Optional<CustomerDto> deleteCustomer(Long customerId);

	public Optional<CustomerDto> getCustomer(Long customerId);

	public List<CustomerDto> findAll();

}
