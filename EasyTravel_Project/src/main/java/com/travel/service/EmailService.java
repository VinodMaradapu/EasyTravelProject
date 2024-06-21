package com.travel.service;


public interface EmailService {

	void sendEmailWithPhotoAndText(String to, String subject, String text, String photoPath);

}
