package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itextpdf.layout.Document;
import com.travel.bean.BookingDto;

@Repository
public interface RideReportRepository extends JpaRepository<BookingDto, Integer> {

	void save(Document document);

}
