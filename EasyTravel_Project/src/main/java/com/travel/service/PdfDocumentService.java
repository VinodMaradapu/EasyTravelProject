package com.travel.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.PdfDocument;

public interface PdfDocumentService {

	PdfDocument savePdfDocument(MultipartFile file) throws IOException;

	Optional<PdfDocument> getPdfDocumentById(String fileName);

}
