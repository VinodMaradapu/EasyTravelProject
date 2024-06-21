package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.bean.VerifyOtpDetails;
@Repository
public interface VerifyOtpDetailsRepository extends JpaRepository<VerifyOtpDetails, Integer>
{
	@Query(value = "select * from verify_otp_details where email=:email and otp=:otp", nativeQuery = true)
	VerifyOtpDetails findByEmailWithOtp(String email, String otp);
	
	@Query(value = "select * from verify_otp_details where phone=:phone and otp=:otp", nativeQuery = true)
	VerifyOtpDetails findByPhoneWithOtp(String phone, String otp);
}
