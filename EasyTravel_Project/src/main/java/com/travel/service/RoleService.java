package com.travel.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travel.bean.ApiResponse;
import com.travel.bean.Role;
import com.travel.repository.RoleRepository;
@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
    private static final Logger log = Logger.getLogger(UserService.class);

	
    public ApiResponse insertRole(Role role) {
        log.info("insert role method started");
        System.out.println(role.toString());
        ApiResponse apiResponse = new ApiResponse();
        try {
            boolean roleExists = roleRepository.existsByRoleName(role.getRoleName());
            if (roleExists) {
                apiResponse.setStatus(true);
                apiResponse.setStatusCode("200");
                apiResponse.setMessage("Already existing role name " + role.getRoleName());
                log.error("Already existing role name " + role.getRoleName());
                return apiResponse;
            }

            Role savedRole = roleRepository.save(role);
            apiResponse.setStatus(true);
            apiResponse.setStatusCode("200");
            apiResponse.setMessage("Inserted successfully role");
            apiResponse.setData(savedRole);
            log.info("Inserted successfully role");
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setStatus(false);
            apiResponse.setStatusCode("400");
            apiResponse.setMessage("Inserted role error");
            log.error("Inserted role error");
            e.printStackTrace();
            return apiResponse;
        }
    }
	
	public ApiResponse getRole(int id) {
		log.info("get role method started");
	    ApiResponse apiResponse = new ApiResponse();
	    try {
	        Role role = roleRepository.findById(id).orElse(null);
	        if (role != null) {
	            apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
	            apiResponse.setMessage("role id found "+id );
	            apiResponse.setData(role);
	            log.info("role id found "+id);
	        } else {
	            apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
	            apiResponse.setMessage("role id not found ");
	            log.info("role id not found ");
	        }
	        return apiResponse;
	    } catch (Exception e) {
	    	 apiResponse.setStatus(true);
	            apiResponse.setStatusCode("400");
	        apiResponse.setMessage("Role id Failed");
	        log.error("Role id Failed");
	        return apiResponse;
	    }
	}

	public ApiResponse getAllRole() {
		ApiResponse apiResponse = new ApiResponse();
		   try {
		       List<Role> roles = roleRepository.findAll();
		       apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
		       apiResponse.setMessage("role retrieved Sucessfully");
		       apiResponse.setData(roles);
		       log.info("role retrieved Sucessfully");
		       return apiResponse;
		   } catch (Exception e) {
			   apiResponse.setStatus(true);
	            apiResponse.setStatusCode("400");
		       apiResponse.setMessage("role retrieved Failed");
		       log.error("role retrieved Failed");
		       return apiResponse;
		   }
	}
	
	public ApiResponse deleteRole(int id) {
		log.info("delete role method started");
		ApiResponse apiResponse = new ApiResponse();
		   try {
		       if (!roleRepository.existsById(id)) {
		    	   apiResponse.setStatus(true);
		            apiResponse.setStatusCode("200");
		           apiResponse.setMessage("delete id not found role in database");
			       log.info("delete id not found role in database");
		           return apiResponse;
		       }

		       roleRepository.deleteById(id);
		       apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
		       apiResponse.setMessage("delete sucessfully "+id);
		       apiResponse.setData(id);
		       log.info("delete sucessfully "+id);
		       return apiResponse;
		   } catch (Exception e) {
			   apiResponse.setStatus(true);
	            apiResponse.setStatusCode("400");
		       apiResponse.setMessage("role delete failed");
		       log.error(" role delete failed");
		       return apiResponse;
		   }
	}
	
	public ApiResponse updateRole(int roleId, Role role) {
		log.info("update id role method started");
	    ApiResponse apiResponse = new ApiResponse();
	    try {
	        Role existingRole = roleRepository.findById(roleId).orElse(null);
	        if (existingRole != null) {
	            existingRole.setRoleName(role.getRoleName());
	            roleRepository.save(existingRole);
	            apiResponse.setStatus(true);
	            apiResponse.setStatusCode("200");
	            apiResponse.setMessage("update sucessfully role");
	            apiResponse.setData(existingRole);
		        log.info("update Sucessfully role");

	        } else {
	        	 apiResponse.setStatus(true);
		            apiResponse.setStatusCode("200");
	            apiResponse.setMessage("update id not found in database");
		        log.info("");

	        }
	        return apiResponse;

	    } catch (Exception e) {
	    	 apiResponse.setStatus(true);
	            apiResponse.setStatusCode("400");
	        apiResponse.setMessage("role update failed");
	        log.error("role update failed");
	        return apiResponse;
	    }
	}
	


}
