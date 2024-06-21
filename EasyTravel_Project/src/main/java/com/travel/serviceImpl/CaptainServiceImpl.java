package com.travel.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;
import com.travel.repository.CaptainRepository;
import com.travel.service.CaptainService;
 
@Service
public class CaptainServiceImpl implements CaptainService{
	
	@Autowired
	CaptainRepository captainRepository;

	@Autowired
	OtpServiceImpl otpServiceImpl;
	
	public ApiResponse registerCaptain(CaptainDto captain){
		ApiResponse apiResponse = new ApiResponse();
		 CaptainDto existMail = captainRepository.findByEmail(captain.getEmail());
		    if (existMail != null) {
	            apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
	            apiResponse.setMessage("captain email already exists");
	            return apiResponse;
	        } 
		  CaptainDto existByPhoneNUmber = captainRepository.findByPhone(captain.getPhone());
		  if (existByPhoneNUmber != null) {
		        apiResponse.setStatus(true);
		        apiResponse.setStatusCode("200");
		        apiResponse.setMessage("captain phone already exists");
		        return apiResponse;
		   }
		CaptainDto saveCaptain= captainRepository.save(captain);
		sendCaptainSavedEmailNotification(saveCaptain);
		apiResponse.setStatus(true);
        apiResponse.setStatusCode("200");
        apiResponse.setMessage("captain registered successfully");
        apiResponse.setData(saveCaptain);
		return apiResponse;
	}

	public ApiResponse getCaptainById(int id) {
		ApiResponse apiResponse = new ApiResponse();
		Optional<CaptainDto> optional=captainRepository.findById(id);
		if(optional.isPresent()) {
			apiResponse.setStatus(true);
			apiResponse.setStatusCode("200");
			apiResponse.setMessage("captain fetched successfully with id "+id);
			apiResponse.setData(optional.get());
		}
		else {
			apiResponse.setStatus(false);
			apiResponse.setStatusCode("200");
			apiResponse.setMessage("captain not found with id "+id);
			return apiResponse;
		}
		return apiResponse;
		
	}

	public ApiResponse getAllCaptains() {
		ApiResponse apiResponse = new ApiResponse();
	    List<CaptainDto> data =  captainRepository.findAll();
		apiResponse.setStatus(true);
		apiResponse.setStatusCode("200");
		apiResponse.setMessage("all captains fetched successfully");
		apiResponse.setData(data);
		return apiResponse;
		
	}

	public ApiResponse updateCaptain(CaptainDto dto) {
		ApiResponse apiResponse = new ApiResponse();
	  Optional<CaptainDto> exist=captainRepository.findById(dto.getId());
	  CaptainDto existByPhoneNUmber = captainRepository.findByPhone(dto.getPhone());
	  if (existByPhoneNUmber != null) {
	        apiResponse.setStatus(true);
	        apiResponse.setStatusCode("200");
	        apiResponse.setMessage("captain phone already exists");
	        return apiResponse;
	   }
	if(exist.isPresent()) {
		  CaptainDto captain= exist.get();
		  captain.setId(dto.getId());
		  captain.setUserName(dto.getUserName());
		  captain.setEmail(dto.getEmail());
		  captain.setPhone(dto.getPhone());
		  captain.setGender(dto.getGender());
		  captain.setPassword(dto.getPassword());
		  captain.setAadharNumber(dto.getAadharNumber());
		  captain.setDrivingLicense(dto.getDrivingLicense());
		CaptainDto updateCaptain = captainRepository.save(captain);
		sendCaptainUpdatedEmailNotification(updateCaptain);
		apiResponse.setStatus(true);
        apiResponse.setStatusCode("200");
        apiResponse.setMessage("captain updated successfully");
        apiResponse.setData(updateCaptain);
		return apiResponse;
	 }
	else {
		apiResponse.setStatus(true);
        apiResponse.setStatusCode("200");
        apiResponse.setMessage("captain not found in database");
        return apiResponse;
	}
   }

	public ApiResponse deleteCaptain(int id) {
		ApiResponse apiResponce = new ApiResponse();
        Optional<CaptainDto> captainDto=captainRepository.findById(id);
        if(captainDto!=null) {
        	captainRepository.delete(captainDto.get());
        	apiResponce.setStatus(true);
        	apiResponce.setStatusCode("200");
        	apiResponce.setMessage("captain deleted successfully");
        	return apiResponce;
        }
        else {
        	apiResponce.setStatus(false);
        	apiResponce.setStatusCode("200");
        	apiResponce.setMessage("captain not found in database");
        	return apiResponce;
		}
	}
	
	private SimpleMailMessage sendCaptainSavedEmailNotification(CaptainDto saveCaptain) {
		 CaptainDto SavedCaptain=captainRepository.findByEmail(saveCaptain.getEmail());
		 try {
		 if(SavedCaptain!=null){
				return otpServiceImpl.sendCaptainSavedEmail(SavedCaptain.getEmail());
			}else {
				return null;
			}
		}catch (Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	  }	
	}
	
	private SimpleMailMessage sendCaptainUpdatedEmailNotification(CaptainDto updateCaptain) {
		CaptainDto updatedCaptain=captainRepository.findByEmail(updateCaptain.getEmail());
		 try {
		 if(updatedCaptain!=null){
				return otpServiceImpl.sendCaptainUpdatedEmail(updatedCaptain.getEmail());
			}else {
				return null;
			}
		}catch (Exception ex) {
		System.out.println(ex.getMessage());
		return null;
	  }	
	}
}

