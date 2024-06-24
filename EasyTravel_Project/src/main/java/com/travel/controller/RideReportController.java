package com.travel.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.bean.BookingDto;
import com.travel.exception.CustomValidationException;
import com.travel.repository.RideReportRepository;
import com.travel.service.RideReportService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RideReportController {

    @Autowired
    private RideReportService reportService;

    @Autowired
    private RideReportRepository rideReportRepository;
    
    @GetMapping("/download/pdf/excel")
    public String downloadPdf(@RequestParam String fileName,String fileType) {
    	if(fileType.equals("pdf")) {
        try {
            List<BookingDto> data = rideReportRepository.findAll();
            byte[] pdfBytes = reportService.generatePdf( data);
            String savePath = "C:\\Users\\MUNNANGI\\OneDrive\\Desktop\\contact\\" + fileName + ".pdf";
            savePdfToFile(pdfBytes, savePath);
            if (pdfBytes != null) {
                reportService.zipFile(savePath+".zip", pdfBytes);
            } else {
               return "zip file download filed";
            }
            File file = new File(savePath);
            return "pdf downloaded successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Filed to download pdf report";
        }
    }
    	else if(fileType.equals("excel")){
    		try {
                List<BookingDto> data = rideReportRepository.findAll();
                byte[] pdfBytes = reportService.generateExcel( data);
                String savePath = "C:\\Users\\MUNNANGI\\OneDrive\\Desktop\\contact\\" + fileName + ".Excel";
                saveExcelToFile(pdfBytes, savePath);
                if (pdfBytes != null) {
                    reportService.zipFile(savePath, pdfBytes);
                } else {
                   return "zip file download filed";
                }
                File file = new File(savePath);
                return "Excel downloaded successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "Filed to download Excel report";
            }
    	}
    	else {
    		throw new CustomValidationException("please provide filetype");
    	}
    }
	private void savePdfToFile(byte[] pdfBytes, String savePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(savePath);
        fos.write(pdfBytes);
        fos.close();
    }
	   private void saveExcelToFile(byte[] pdfBytes, String savePath) throws IOException {
	    	 FileOutputStream fos = new FileOutputStream(savePath);
	         fos.write(pdfBytes);
	         fos.close();		
		}
}

