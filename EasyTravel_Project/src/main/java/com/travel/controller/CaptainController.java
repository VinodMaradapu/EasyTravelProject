package com.travel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;
import com.travel.service.CaptainService;

@RestController
public class CaptainController {
	
	@Autowired
	CaptainService captainService;
	
	@PostMapping("/registerCaptain")
	public ApiResponse saveCaptain(@RequestBody CaptainDto captain){
		return captainService.registerCaptain(captain);
	}
	
	@GetMapping("/getAllCaptains")
	public ApiResponse getCaptain(){
		return captainService.getAllCaptains();
	}
	@GetMapping("/getCaptainById/{id}")
	public ApiResponse getByIdCaptain(@PathVariable int id){
		return captainService.getCaptainById(id);
	}
	@DeleteMapping("delete/{id}")
	public ApiResponse deleteById(@PathVariable int id){
		return captainService.deleteCaptain(id);
	}
	@PutMapping("/update/{id}")
	public ApiResponse updateCustomer(@RequestBody CaptainDto customer){
		return captainService.updateCaptain(customer);
	}
}
