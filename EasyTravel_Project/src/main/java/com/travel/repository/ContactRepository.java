package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.bean.ContactUs;
@Repository
public interface ContactRepository extends JpaRepository<ContactUs, Long> {

}
