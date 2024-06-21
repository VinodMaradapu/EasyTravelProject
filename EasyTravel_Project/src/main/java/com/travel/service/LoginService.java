package com.travel.service;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;
import com.travel.bean.ForgotPasswordRequestDTO;
import com.travel.bean.LoginCaptain;
import com.travel.bean.SendOtpEmail;
import com.travel.bean.SendOtpPhone;
import com.travel.bean.VerifyOtpDetails;

public interface LoginService {

	String sendOtpByEmail(SendOtpEmail sendOtpRequest);

	String sendOtpByPhone(SendOtpPhone sendOtpPhone);

	String verifyOtpPhone(VerifyOtpDetails otpDetails);

	ApiResponse login(LoginCaptain loginCaptain);

	CaptainDto forgotPassword(ForgotPasswordRequestDTO dto);

}
