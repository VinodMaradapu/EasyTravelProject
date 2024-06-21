package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.bean.CaptainDto;
@Repository
public interface CaptainRepository extends JpaRepository<CaptainDto, Integer>{
	
	    @Query("SELECT c FROM CaptainDto c WHERE c.email = :email")
	    CaptainDto findByEmail(@Param("email") String email);
	    
	    @Query("SELECT c FROM CaptainDto c WHERE c.phone = :phone")
	    CaptainDto findByPhone(@Param("phone") String phone);

	    @Query("SELECT u FROM CaptainDto u WHERE (u.email = :emailOrPhone OR u.phone = :emailOrPhone) AND u.password = :password")
	    CaptainDto findByEmailorPhoneNumberAndPassword(@Param("emailOrPhone") String emailOrPhone, @Param("password") String password);
//
//	    @Query("SELECT c FROM CaptainDto c WHERE c.firstName = :firstName")
//	    boolean existsByName(@Param("firstName")String firstName);
//	    
//        @Query("SELECT c FROM CaptainDto c WHERE c.phone = :phone")
//        boolean existsByPhoneNumber(@Param("phone") String phone);
}
