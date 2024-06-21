package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.bean.CaptainDto;


@Repository
public interface LoginRepository extends JpaRepository<CaptainDto, Integer> {

	
//    Customer findByEmailAndPassword(String email, String password);
//    
//    @Query("SELECT u FROM customer u WHERE (u.email = :emailOrPhone OR u.phone = :emailOrPhone) AND u.password = :password")
//    Customer findByEmailOrPhoneAndPassword(@Param("emailOrPhone") String emailOrPhone, @Param("password") String password);
}