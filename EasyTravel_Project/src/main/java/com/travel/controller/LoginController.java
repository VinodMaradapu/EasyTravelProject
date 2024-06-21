package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;
import com.travel.bean.ForgotPasswordRequestDTO;
import com.travel.bean.SendOtpPhone;
import com.travel.bean.VerifyOtpDetails;
import com.travel.service.LoginService;
import com.travel.bean.LoginCaptain;
import com.travel.bean.SendOtpEmail;


@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody LoginCaptain loginCaptain) {
		System.out.println(loginCaptain.toString());
		return loginService.login(loginCaptain);
	}
	@PostMapping("/sendOTPtophone")
	public String sendOtpPhone(@RequestBody SendOtpPhone sendOtpPhone) {
		return loginService.sendOtpByPhone(sendOtpPhone);
	}
	@PostMapping("/sendOTPtoMail")
	public String sendOtpByEmail(@RequestBody SendOtpEmail sendOtpRequest) {
		return loginService.sendOtpByEmail(sendOtpRequest);
	}
	@PostMapping("/verifyOTPPhone")
	public String verifyOtpPhone(@RequestBody VerifyOtpDetails otpDetails) {
		return loginService.verifyOtpPhone(otpDetails);
	}
	@PostMapping("/forgot-password")
	public  CaptainDto forgotPassword(@RequestBody ForgotPasswordRequestDTO dto) {
		return loginService.forgotPassword(dto);
	}
}
