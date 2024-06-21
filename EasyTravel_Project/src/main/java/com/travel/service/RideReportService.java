package com.travel.service;

import java.util.List;

import com.travel.bean.BookingDto;
import com.travel.bean.FileDto;

public interface RideReportService{

	String generatePdf(FileDto dto,  List<BookingDto> data);



}
