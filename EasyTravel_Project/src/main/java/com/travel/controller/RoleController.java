package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.ApiResponse;
import com.travel.bean.Role;
import com.travel.service.RoleService;

import jakarta.validation.Valid;

@RestController 
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	    @PostMapping("/role/save")
		public ApiResponse insertRole(@Valid @RequestBody Role role)
		{
			return roleService.insertRole(role);
		}
	  
	    @GetMapping("role/{id}")
		public ApiResponse getRole(@PathVariable int id)
		{
			return roleService.getRole(id);
		}
	  
		@GetMapping("/role/getall")
		public ApiResponse getAllRole()
		{
			return roleService.getAllRole();
		}
		
		@DeleteMapping("/role/{id}")
		public ApiResponse deleteRole(@PathVariable int id) 
		{
			return roleService.deleteRole(id);
		}	 
		 
		@PutMapping("/role/{roleId}")
		public ApiResponse updateRole(@PathVariable int roleId, @RequestBody Role role) {
		     return roleService.updateRole(roleId, role);
		}

}
