package com.travel.serviceImpl;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.Data;

@Component
@Data
@Service
public class OtpServiceImpl {

	 @Value("${twilio.account-sid}")
	    private String accountSid;

	    @Value("${twilio.auth-token}")
	    private String authToken;

	    @Value("${twilio.from-number}")
	    private String fromNumber;
	
	    @Value("${spring.mail.username}")
	    private String username;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public String generateOtp() {
		 int otp = 100000 + new Random().nextInt(900000);
		 return String.valueOf(otp);
//		    return String.format("%06d", (int) (Math.random() * 10000));
	}
	
	public String sendOtpByEmail(String email, String otp) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(email);
	    message.setSubject("Easy Travels");
	    message.setText(otp + " is your Easy Travel OTP. Don't share it with anyone. ");
	    javaMailSender.send(message);    
	    return otp;
	}
	public void sendOtpByPhone(String target, String otp) {
        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                    new PhoneNumber(target),
                    new PhoneNumber(fromNumber), // Use another number if specified
                    "Easy Travel Your OTP code: " + otp
            ).create();
        } catch (Exception e) {
            System.out.println("Failed to send OTP: " + e.getMessage());
        }
    }

	public SimpleMailMessage sendCaptainSavedEmail(String target) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(target);
		message.setSubject("Easy Travels");
		message.setText("Captain created successfully");
		javaMailSender.send(message);	
		return message;
	}

	public SimpleMailMessage sendCaptainUpdatedEmail(String target) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(target);
		message.setSubject("Easy Travels");
		message.setText("Captain updated successfully");
		javaMailSender.send(message);	
		return message;
	}
}
