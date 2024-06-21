package com.travel.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import com.travel.bean.BookingDto;
import com.travel.bean.FileDto;
import com.travel.exception.CustomValidationException;
import com.travel.repository.RideReportRepository;
import com.travel.service.RideReportService;

@Service
public class RideReportServiceImpl implements RideReportService {

    @Autowired
    private RideReportRepository reportRepository;

	public String generatePdf(FileDto dto, List<BookingDto> data) {
        List<String> columnsName = Arrays.asList("Customer Name", "From Location", "To Location", "Distance",
                "Captain Name", "Payment");
        String pdfFilePath = dto.getReportName() + ".pdf";

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            Paragraph title = new Paragraph("Ride History Report")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);
            document.add(title);

            Table table = new Table(6); // 6 columns
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

            Path path = Paths.get(pdfFilePath);
            Files.write(path, baos.toByteArray());


        } catch (IOException e) {
            throw new CustomValidationException("Failed to generate PDF report");
        }

        return pdfFilePath;
    }

    private void zipAndDownload(String pdfFilePath, String reportName) throws FileNotFoundException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zipOut = new ZipOutputStream(baos);
             FileInputStream fis = new FileInputStream(pdfFilePath)) {

            ZipEntry zipEntry = new ZipEntry(reportName + ".pdf");
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = StreamUtils.copyToByteArray(fis);
            zipOut.write(bytes, 0, bytes.length);
            zipOut.closeEntry();

            zipOut.finish();

            byte[] zipBytes = baos.toByteArray();

        } catch (IOException e) {
            throw new CustomValidationException("Failed to zip PDF file");
        } finally {
            File pdfFile = new File(pdfFilePath);
            if (pdfFile.exists()) {
                pdfFile.delete();
            }
        }
    }

}
