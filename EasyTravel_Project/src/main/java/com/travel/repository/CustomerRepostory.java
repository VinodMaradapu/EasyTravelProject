package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.bean.Customer;

@Repository
public interface CustomerRepostory extends JpaRepository<Customer, Integer>{
	    @Query("SELECT c FROM Customer c WHERE c.email = :email")
	    Customer findByEmail(@Param("email") String email);
	    
	    @Query("SELECT c FROM Customer c WHERE c.phone = :phone")
   Customer findByPhone(@Param("phone") String phone);
	    


}
