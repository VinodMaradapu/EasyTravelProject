package com.travel.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import com.travel.bean.BookingDto;
import com.travel.repository.RideReportRepository;
import com.travel.service.RideReportService;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

@Service
public class RideReportServiceImpl implements RideReportService {

    @Autowired
    private RideReportRepository reportRepository;
    
    public byte[] generatePdf( List<BookingDto> data) {
        List<String> columnsName = Arrays.asList("Customer Name", "From Location", "To Location", "Distance",
                "Captain Name", "Payment");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {
            Paragraph title = new Paragraph("Ride History Report")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);
            document.add(title);
            Table table = new Table(columnsName.size());
            table.setWidth(520);
            for (String columnName : columnsName) {
                table.addHeaderCell(columnName);
            }
            for (BookingDto booking : data) {
                table.addCell(booking.getCustomerName());
                table.addCell(booking.getFrom());
                table.addCell(booking.getTo());
                table.addCell(String.valueOf(booking.getDistance()));
                table.addCell(booking.getCaptainName());
                table.addCell(String.valueOf(booking.getPayment()));
            }
            document.add(table);
            document.close();
            return baos.toByteArray(); 
        } catch (IOException e) {
            e.printStackTrace(); 
            return null;
        }
    }


	@Override
	public byte[] generateExcel(List<BookingDto> data) {
		        try {
		            ByteArrayOutputStream baos = new ByteArrayOutputStream();
		            HSSFWorkbook workbook = new HSSFWorkbook();
		            HSSFSheet sheet = workbook.createSheet("Booking Data");

		            String[] header = new String[] {"Customer Name", "From Location", "To Location", "Distance",
		                    "Captain Name", "Payment"};
		            HSSFRow headerRow = sheet.createRow(0);
		            HSSFCellStyle style = createCellColor(workbook);

		            int count = 0;
		            for (String columnName : header) {
		                Cell cell = headerRow.createCell(count++);
		                cell.setCellValue(columnName);
		                cell.setCellStyle(style);
		            }

		            int rowIdx = 1;
		            for (BookingDto booking : data) {
		                Row row = sheet.createRow(rowIdx++);
		                row.createCell(0).setCellValue(booking.getCustomerName());
		                row.createCell(1).setCellValue(booking.getFrom());
		                row.createCell(2).setCellValue(booking.getTo());
		                row.createCell(3).setCellValue(booking.getDistance());
		                row.createCell(4).setCellValue(booking.getCaptainName());
		                row.createCell(5).setCellValue(booking.getPayment());
		            }

		            for (int col = 0; col < header.length; col++) {
		                sheet.autoSizeColumn(col);
		            }

		            workbook.write(baos);
		            workbook.close();

		            return baos.toByteArray();
		        } catch (IOException e) {
		            e.printStackTrace();
		            return null;
		        }
		  }
		 
	private HSSFCellStyle createCellColor(HSSFWorkbook hssfWorkbook) {
		HSSFCellStyle cellStyle=hssfWorkbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		Font font=hssfWorkbook.createFont();
		font.setBold(true);
		return cellStyle;
	}
	@Override
		    public void zipFile(String savePath, byte[] pdfBytes) {
		        try (FileOutputStream fos = new FileOutputStream(savePath);
		             ZipOutputStream zipOut = new ZipOutputStream(fos);
		             ByteArrayInputStream bis = new ByteArrayInputStream(pdfBytes)) {
		            
		            ZipEntry zipEntry = new ZipEntry("RideHistoryReport.zip");
		            zipOut.putNextEntry(zipEntry);
		            
		            byte[] buffer = new byte[1024];
		            int bytesRead;
		            while ((bytesRead = bis.read(buffer)) != -1) {
		                zipOut.write(buffer, 0, bytesRead);
		            }
		            zipOut.closeEntry();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    
	}
}