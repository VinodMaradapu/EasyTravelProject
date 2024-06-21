package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.Customer;
import com.travel.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	@PostMapping("/save")
	public Customer saveCustomer(@RequestBody Customer customer)
	{
		return service.saveCustomer(customer);
	}
	
	@GetMapping("/getAll")
	public List<Customer> getCustomer()
	{
		return service.getCustomer();
	}
	@GetMapping("/getById/{id}")
	public Customer getByIdCustomer(@PathVariable int id)
	{
		return service.getByIdCustomer(id);
	}
	@DeleteMapping("/{id}")
	public String deleteById(int id)
	{
		return service.deleteByIdCustomer(id);
	}
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable int id)
	{
		return service.updateCustomer(customer,id);
	}
	

}
