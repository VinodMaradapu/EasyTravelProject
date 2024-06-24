package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("SELECT c FROM users c WHERE c.email = :email")
    User findByEmail(@Param("email") String email);
    
    @Query("SELECT c FROM users c WHERE c.phone = :phone")
    User findByPhone(@Param("phone") String phone);
//	  
//    @Query("SELECT u FROM user u WHERE (u.email = :emailOrPhone OR u.phone = :emailOrPhone) AND u.password = :password")
//    User findByEmailOrPhoneAndPassword(@Param("emailOrPhone") String emailOrPhone, @Param("password") String password);

}
