package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.bean.User;


@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

	
    
  }