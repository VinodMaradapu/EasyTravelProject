package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.service.EmailService;

@RestController
public class EmailController {

	    @Autowired
	    private EmailService emailService;

	    @GetMapping("/sendEmail")
	    public String sendEmailWithPhotoAndText() {
	        String to = "mvinod2025@gmail.com";
	        String subject = "Easy travel";
	        String text = "Captain Registered Successfully";
	        String photoPath = "C:\\Users\\MUNNANGI\\OneDrive\\Desktop\\email.jpg";

	        emailService.sendEmailWithPhotoAndText(to, subject, text, photoPath);
	        return "Email sent successfully!";
	    }
}
