package com.travel.serviceImpl;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.kernel.geom.PageSize;

import com.travel.bean.BookingDto;
import com.travel.bean.FileDto;
import com.travel.exception.CustomValidationException;
import com.travel.repository.RideReportRepository;
import com.travel.service.RideReportService;

@Service
public class RideReportServiceImpl implements RideReportService {

    @Autowired
    private RideReportRepository reportRepository;

    @Override
    public void generatePdf(FileDto dto, List<BookingDto> data) {
        try {
            pdfReport(dto, data);
            pdfFilePath = dto.getReportName() + ".pdf";
            zipFile(pdfFilePath, dto.getReportName());
        } catch (Exception e) {
            throw new CustomValidationException("Failed to generate PDF report");
        }
    }

    private String pdfFilePath = ""; 

    public void pdfReport(FileDto dto, List<BookingDto> data) throws FileNotFoundException {
        List<String> columnsName = Arrays.asList("Customer Name", "From Location", "To Location", "Distance",
                "Captain Name", "Payment");
        String filePath = "\\" + dto.getReportName() + ".pdf";

        PdfWriter writer = new PdfWriter(pdfFilePath + filePath);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4);

        Paragraph title = new Paragraph("Ride History Report").setTextAlignment(TextAlignment.CENTER).setBold()
                .setFontSize(20);
        document.add(title);

        float[] columnWidths = { 1, 1, 1, 1, 1, 1 }; 
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        for (String columnName : columnsName) {
            Cell cell = new Cell().add(new Paragraph(columnName)).setFontSize(8)
                    .setHeight(28.0f);
            table.addHeaderCell(cell);
        }
        for (BookingDto booking : data) {
            table.addCell(new Cell().add(new Paragraph(booking.getCustomerName())));
            table.addCell(new Cell().add(new Paragraph(booking.getFrom())));
            table.addCell(new Cell().add(new Paragraph(booking.getTo())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(booking.getDistance()))));
            table.addCell(new Cell().add(new Paragraph(booking.getCaptainName())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(booking.getPayment()))));
        }
        document.add(table);
        document.close(); 
        reportRepository.save(document);
    }

    private void zipFile(String pdfFilePath, String reportName) {
    }
}
