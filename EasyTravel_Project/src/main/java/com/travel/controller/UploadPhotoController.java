package com.travel.controller;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.ApiResponse;
import com.travel.service.UploadPhotoService;


@RestController
@RequestMapping("/api")
public class UploadPhotoController {

	@Autowired
	UploadPhotoService photoService;
	   @PostMapping("/image")
	    public ApiResponse uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		   return photoService.uploadImage(file);
	    }
	   @GetMapping("/images/{name}")
	    public ResponseEntity<Resource> getImageByName1(@PathVariable String name) throws IOException {
	        ApiResponse response =photoService.getPhotoByname(name);
	        byte[] imageData = (byte[]) response.getData();

	        ByteArrayResource resource = new ByteArrayResource(imageData);
	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_JPEG) // Set appropriate content type
	                .body(resource);
	    }
}
