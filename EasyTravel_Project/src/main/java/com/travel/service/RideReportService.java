package com.travel.service;

import java.util.List;

import com.travel.bean.BookingDto;
import com.travel.bean.FileDto;

public interface RideReportService{

	void generatePdf(FileDto dto,  List<BookingDto> data);



}
