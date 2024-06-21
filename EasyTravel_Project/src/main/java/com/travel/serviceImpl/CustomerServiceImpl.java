package com.travel.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.Customer;
import com.travel.repository.CustomerRepostory;
import com.travel.service.CustomerService;
 
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepostory repository;
	
	public Customer saveCustomer( Customer customer)
	{
		return repository.save(customer);
	}

	public List<Customer> getCustomer() {
		return repository.findAll();
	}
	 
	public Customer getByIdCustomer(int id) {
	     Optional<Customer> c=repository.findById(id);
	     if(c.isPresent())
	     {
	    	 return c.get();
	     }
	     return null;
	}
	
	public String deleteByIdCustomer(int id)
	{
		Customer customer=getByIdCustomer(id);
		if(customer!=null)
		{
			repository.delete(customer);
			return "deleted sucessfully "+id;
		}
		return id+" id not found in database";
	}

	public Customer updateCustomer(Customer customer ,int id) {

		Customer cust=getByIdCustomer(id);
		if(cust!=null)
		{
			cust.setFirstName(customer.getFirstName());
			cust.setLastName(customer.getLastName());
			cust.setEmail(customer.getEmail());
			cust.setPhone(customer.getPhone());
			cust.setPassword(customer.getPassword());
			cust.setGender(customer.getGender());
			return repository.save(cust);
		}
		return null;
	}

}
