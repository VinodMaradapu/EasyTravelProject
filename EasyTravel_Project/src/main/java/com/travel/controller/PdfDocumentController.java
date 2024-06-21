package com.travel.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;

 import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.PdfDocument;
import com.travel.service.PdfDocumentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pdf")
public class PdfDocumentController {

    @Autowired
    private PdfDocumentService pdfDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdfFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            PdfDocument savedPdf = pdfDocumentService.savePdfDocument(file);
            return ResponseEntity.ok("File uploaded successfully! Id: " + savedPdf.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/download/{pdfId}")
    public ResponseEntity<Resource> downloadPdf(@RequestParam String fileName) throws IOException {
        Optional<PdfDocument> pdfDocumentOptional = pdfDocumentService.getPdfDocumentById(fileName);

        if (pdfDocumentOptional.isPresent()) {
            PdfDocument pdfDocument = pdfDocumentOptional.get();

            ByteArrayResource resource = new ByteArrayResource(pdfDocument.getData());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfDocument.getFileName());
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
}
}
