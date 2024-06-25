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
import com.travel.bean.User;
import com.travel.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
		@PostMapping()
		public ApiResponse insertUser(@Valid @RequestBody User user)
		{
			return userService.insertUser(user);
		}
		
		@GetMapping("/{id}")
		public ApiResponse getUser(@PathVariable int id)
		{
			return userService.getUser(id);
		}
		
		@GetMapping()
		public ApiResponse getAllUser()
		{
			return userService.getAllUser();
		}
		
		@DeleteMapping("/{id}")
	    public ApiResponse deleteUser( @PathVariable int id) 
		{ 
				return userService.deleteUser(id);
		}
	 
	    @PutMapping()
	    public ApiResponse updateUser( @RequestBody User user) 
	    {
			return userService.updateUser( user);
	    }
	  	
	

}
