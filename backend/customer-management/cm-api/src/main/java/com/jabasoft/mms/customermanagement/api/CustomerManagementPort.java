package com.jabasoft.mms.customermanagement.api;

import com.jabasoft.mms.customermanagement.api.dto.CustomerDto;

public interface CustomerManagementPort {

	public boolean addCustomer(CustomerDto customerDto);

	public boolean deleteCustomer(String customerId);

	public CustomerDto getCustomer(String customerId);

}
