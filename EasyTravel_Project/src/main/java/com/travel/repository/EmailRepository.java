package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.bean.EmailDetailsDto;

public interface EmailRepository extends JpaRepository<EmailDetailsDto, Integer> {

}
