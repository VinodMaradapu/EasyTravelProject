package com.travel.service;

import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travel.bean.ApiResponse;
import com.travel.bean.User;
import com.travel.bean.util.JWTService;
import com.travel.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
  	UserRepository userRepository;
	@Autowired
	JWTService jwtService;
 
    private static final Logger log = Logger.getLogger(UserService.class);

    public ApiResponse insertUser(User user) {
		log.info("insert user method started");
        ApiResponse apiResponse = new ApiResponse();
        try {
            User existingUserByEmail = userRepository.findByEmail(user.getEmail());
            if (existingUserByEmail != null) {
                apiResponse.setStatus(true);
                apiResponse.setStatusCode("200");
                apiResponse.setMessage("email already found");
                log.info("email already found");
                return apiResponse;
            }
            
            User existingUserByPhone = userRepository.findByPhone(user.getPhone());
            if (existingUserByPhone != null) {
            	   apiResponse.setStatus(false);
                   apiResponse.setStatusCode("200");
                apiResponse.setMessage("phone number already found");
                log.info("phone number already found");
                return apiResponse;
            }
            
            User savedUser = userRepository.save(user);
            apiResponse.setStatus(true);
            apiResponse.setStatusCode("200");
            apiResponse.setMessage("user inserted sucessfully");
            apiResponse.setData(savedUser);
            log.info("user inserted sucessfully");
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setStatus(false);
            apiResponse.setStatusCode("400");
            apiResponse.setMessage("user inserted failed");
            log.error("user inserted failed");
            return apiResponse;
        }
    }
    
    public ApiResponse getUser(int id) {
		log.info("user method started "+id);
       ApiResponse apiResponse = new ApiResponse();
       try {
    	  
           Optional<User> optionalUser = userRepository.findById(id);
           if (optionalUser.isPresent()) {
        	   User User = optionalUser.get();
        	   apiResponse.setStatus(true);
               apiResponse.setStatusCode("200");
               apiResponse.setMessage("user found id "+id);
               apiResponse.setData(User);
               log.info("user id already found "+id);
           } else {
        	   apiResponse.setStatus(true);
               apiResponse.setStatusCode("200");
               apiResponse.setMessage( "user not found ");
               log.info("user not found");
           }
           return apiResponse;
       } catch (Exception e) {
    	   apiResponse.setStatus(false);
           apiResponse.setStatusCode("400");
           apiResponse.setMessage("user get error");
           log.error("user get error");
           return apiResponse;
       }
   }

	 public ApiResponse getAllUser() {
	   ApiResponse apiResponse = new ApiResponse();
	   try {
	       List<User> Users = userRepository.findAll();
	       apiResponse.setStatus(true);
           apiResponse.setStatusCode("200");
	       apiResponse.setMessage("user retrieved sucessfully ");
	       apiResponse.setData(Users);
	       log.info("user retrieved sucessfully");
	       return apiResponse;
	   } catch (Exception e) {
		   apiResponse.setStatus(false);
           apiResponse.setStatusCode("400");
	       apiResponse.setMessage("user retrieved error");
	       log.error("user retrieved error");
	       return apiResponse;
	   }
	}

	public ApiResponse deleteUser(int id) {
		log.info("delete method started "+id);
	   ApiResponse apiResponse = new ApiResponse();
	   try {
		  
           
	       if (!userRepository.existsById(id)) {
	    	   apiResponse.setStatus(true);
               apiResponse.setStatusCode("200");  
               apiResponse.setMessage("delete not found user "+id);
		       log.info("delete not found user "+id);
	           return apiResponse;
	       }
	
	       userRepository.deleteById(id);
	       apiResponse.setStatus(true);
           apiResponse.setStatusCode("200");
	       apiResponse.setMessage("delete sucessfully  user"+id);
	       apiResponse.setData(id);
	       log.info("delete sucessfully user ");
	       return apiResponse;
	   } catch (Exception e) {
		   apiResponse.setStatus(false);
           apiResponse.setStatusCode("400");
	       apiResponse.setMessage("delete user error");
	       log.error("delete user error");
	       return apiResponse;
	   }
	}
	
	public ApiResponse updateUser(User user) {
		log.info("update method started "+user);
	       ApiResponse apiResponse = new ApiResponse();
	       try {
	    	  
	           if (user != null && userRepository.existsById(user.getUserId())) {
	               User exisitingUser = userRepository.findById(user.getUserId()).orElse(null);
	            if (exisitingUser != null) {
	            	exisitingUser.setPassword(user.getPassword());
	            	exisitingUser.setPhone(user.getPhone());
	            	exisitingUser.setFirstName(user.getFirstName());
	            	exisitingUser.setLastName(user.getLastName());
	            	exisitingUser.setAddress(user.getAddress());
	            	exisitingUser.setDateOfBirth(user.getDateOfBirth());
	            	exisitingUser.setGender(user.getGender());
	            	exisitingUser.setEmail(user.getEmail());
	               userRepository.save(exisitingUser);
	               apiResponse.setStatus(true);
	                apiResponse.setStatusCode("200");
	               apiResponse.setMessage("update sucessfully user");
	               apiResponse.setData(exisitingUser);
		           log.info("update sucessfully user ");
	           } 
	           }else {
	               User savedUser = userRepository.save(user);
	               apiResponse.setStatus(true);
	                apiResponse.setStatusCode("200");
	               apiResponse.setMessage("user inserted sucessfullly "+user);
	               apiResponse.setData(savedUser);
		           log.info("user inserted sucessfully "+user);
	           }
	           return apiResponse;
	       
	       }catch (Exception e) {
	    	   apiResponse.setStatus(false);
               apiResponse.setStatusCode("400");
	           apiResponse.setMessage("update user error");
	           log.error("update user error ");
	           return apiResponse;
	       }
	     }

	








}
