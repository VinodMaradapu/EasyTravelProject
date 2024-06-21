package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.bean.BookingDto;

@Repository
public interface BookingRepository extends JpaRepository<BookingDto, Integer>{

}
