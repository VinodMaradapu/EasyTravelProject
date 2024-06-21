package com.travel.service;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.ApiResponse;

public interface UploadPhotoService {
		
	ApiResponse uploadImage(MultipartFile file) throws IOException;

	ApiResponse getPhotoByname(String name) throws IOException;
}
