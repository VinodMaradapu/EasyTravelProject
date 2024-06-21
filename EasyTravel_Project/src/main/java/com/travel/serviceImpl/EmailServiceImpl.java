package com.travel.serviceImpl;

import org.springframework.stereotype.Service;

import com.travel.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class EmailServiceImpl implements EmailService{

	    @Autowired
	    private JavaMailSender emailSender;

		public void sendEmailWithPhotoAndText(String to, String subject, String text, String photoPath) {
	        try {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setTo(to);
	            helper.setSubject(subject);
	            
	            // Set the text body of the email
	            helper.setText(text);

	            // Attach the photo
	            Path path = Paths.get(photoPath);
	            byte[] bytes = Files.readAllBytes(path);
	            ByteArrayResource resource = new ByteArrayResource(bytes);
	            helper.addAttachment("email.jpg", resource);

	            emailSender.send(message);
	        } catch (MessagingException | IOException e) {
	            e.printStackTrace();
	            // Handle exception
	        }
	    }
}