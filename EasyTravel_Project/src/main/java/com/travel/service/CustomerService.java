package com.travel.service;

import java.util.List;

import com.travel.bean.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer);

	List<Customer> getCustomer();

	Customer getByIdCustomer(int id);

	Customer updateCustomer(Customer customer, int id);

	String deleteByIdCustomer(int id);

}
