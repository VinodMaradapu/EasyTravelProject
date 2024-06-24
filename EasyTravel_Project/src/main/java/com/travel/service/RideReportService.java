package com.travel.service;

import java.util.List;

import com.travel.bean.BookingDto;

public interface RideReportService{

	byte[] generatePdf( List<BookingDto> data);

	byte[] generateExcel( List<BookingDto> data);

	void zipFile(String savePath, byte[] pdfBytes);

}
