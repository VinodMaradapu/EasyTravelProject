package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.ContactUs;
import com.travel.service.ContactService;

@RestController
public class ContactController {

	@Autowired
	ContactService contactService;
	  @PostMapping("/contact-us")
	    public String submitContactMessage(@RequestBody ContactUs message) {
		  contactService.saveContactMessage(message);
	        return "Contact message received and saved successfully!";
	    }
	
}
