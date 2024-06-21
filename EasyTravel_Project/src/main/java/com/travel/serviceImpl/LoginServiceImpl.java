package com.travel.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;
import com.travel.bean.ForgotPasswordRequestDTO;
import com.travel.bean.SendOtpPhone;
import com.travel.bean.VerifyOtpDetails;
import com.travel.bean.LoginCaptain;
import com.travel.bean.SendOtpEmail;
import com.travel.bean.util.JWTService;
import com.travel.repository.CaptainRepository;
import com.travel.repository.LocationRepository;
import com.travel.repository.LoginRepository;
import com.travel.repository.VerifyOtpDetailsRepository;
import com.travel.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	CaptainRepository captainRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	VerifyOtpDetailsRepository verifyOtpDetailsRepository;
	 
	@Autowired
	OtpServiceImpl otpService;
	
	@Autowired
	JWTService jwtservice;
	
	@Autowired
	LocationRepository locationRepository;
    
	public String sendOtpByEmail(SendOtpEmail sendOtpEmail) {
	    CaptainDto captain = captainRepository.findByEmail(sendOtpEmail.getEmail());
	    try {
	        if (captain != null) {
	            String otp = otpService.generateOtp();
	            otpService.sendOtpByEmail(captain.getEmail(), otp);
	            VerifyOtpDetails verifyOtp = new VerifyOtpDetails();
	            verifyOtp.setEmail(captain.getEmail());
	            verifyOtp.setOtp(otp);
	            verifyOtpDetailsRepository.save(verifyOtp);
	            return "otp sent to email successfully";
	        } else {
	            System.out.println("Email not found in database: " + sendOtpEmail.getEmail());
	            return null;
	        }
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
     }
	public String sendOtpByPhone(SendOtpPhone sendOtpPhone) {
		CaptainDto captain=captainRepository.findByPhone(sendOtpPhone.getPhone());
		try {
			if(captain!=null){
		      String otp = otpService.generateOtp();
		      otpService.sendOtpByPhone(captain.getPhone(), otp);
		      VerifyOtpDetails verifyOtp=new VerifyOtpDetails();
			  verifyOtp.setPhone(captain.getPhone());
			  verifyOtp.setOtp(otp);
			  verifyOtpDetailsRepository.save(verifyOtp);
	          return "otp sent to phone number "+captain.getPhone() + "is "+otp;
		}else{
				System.out.println("phone number not found in database");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String verifyOtpPhone(VerifyOtpDetails otpDetails) {
	    try {
	        VerifyOtpDetails details = verifyOtpDetailsRepository.findByPhoneWithOtp(otpDetails.getPhone(), otpDetails.getOtp());
	        if (details == null) {
	            return "invalid otp ";
	        }else {
	            return "otp verified successfully";
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}

	public VerifyOtpDetails verifyOtpEmail(VerifyOtpDetails otpDetails) {
		try {
		VerifyOtpDetails details=verifyOtpDetailsRepository.findByEmailWithOtp(otpDetails.getEmail(), otpDetails.getOtp());
		if (details == null) {
			System.out.println("invalid otp ");
			return details;
	        }else{
			System.out.println("otp verified sucessfully");
			return otpDetails;
	     	}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public ApiResponse login(LoginCaptain loginCaptain) {
		ApiResponse apiResponce=new ApiResponse();
		try {
			CaptainDto captainDto=captainRepository.findByEmailorPhoneNumberAndPassword(loginCaptain.getEmailOrPhone(),loginCaptain.getPassword());
			if(captainDto!=null) {
	            String token = jwtservice.generateToken(captainDto.getEmail(),captainDto.getUserName());
	            apiResponce.setStatus(true);
	            apiResponce.setStatusCode("200");
	            apiResponce.setMessage("successfully token generated");
	            apiResponce.setData(token);
	            return apiResponce;
			}else {
				apiResponce.setStatus(false);
	            apiResponce.setStatusCode("200");
	            apiResponce.setMessage("invalid data");
	            return apiResponce;
			}
		} catch (Exception e) {
			apiResponce.setStatus(false);
            apiResponce.setStatusCode("500");
            apiResponce.setMessage(e.getMessage());
            return apiResponce;
		}
	}
	
	@Override
	public CaptainDto forgotPassword(ForgotPasswordRequestDTO dto) {
		CaptainDto captainDto=new CaptainDto();
		if(dto.getEnterNewPassword().equals(dto.getConfirmNewPassword())) {
			captainDto.setPassword(dto.getEnterNewPassword());
		}
		return captainRepository.save(captainDto);
	}

}
