package com.travel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.BookingDto;
import com.travel.bean.FileDto;
import com.travel.repository.RideReportRepository;
import com.travel.service.RideReportService;

@RestController
public class RideReportController {

	@Autowired
	RideReportService reportService;
	   @Autowired
	    private RideReportRepository reportRepository;
	
	   @GetMapping("/generate")
	    public String generatePdf(@RequestBody FileDto dto) {
	        List<BookingDto> data=reportRepository.findAll();
	        reportService.generatePdf(dto, data);
	        return "PDF generated successfully!";
	    }
}
