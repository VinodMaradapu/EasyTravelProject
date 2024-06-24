package com.travel.service;



import com.travel.bean.SendOtpEmail;
import com.travel.bean.SendOtpPhone;
import com.travel.bean.VerifyOtpDetails;

public interface LoginService {

	String sendOtpByEmail(SendOtpEmail sendOtpRequest);

	String sendOtpByPhone(SendOtpPhone sendOtpPhone);

	String verifyOtpPhone(VerifyOtpDetails otpDetails);



}
