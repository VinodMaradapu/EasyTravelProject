package com.travel.serviceImpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.travel.bean.PdfDocument;
import com.travel.repository.PdfDocumentRepository;
import com.travel.service.PdfDocumentService;
@Service
public class PdfDocumentServiceImpl implements PdfDocumentService{


    @Autowired
    private PdfDocumentRepository pdfDocumentRepository;

    @Override
    public PdfDocument savePdfDocument(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.setFileName(fileName);
        pdfDocument.setData(file.getBytes());
        return pdfDocumentRepository.save(pdfDocument);
    }

	@Override
	public Optional<PdfDocument> getPdfDocumentById(String fileName) {
	return	pdfDocumentRepository.findByFileName(fileName);
	}

}