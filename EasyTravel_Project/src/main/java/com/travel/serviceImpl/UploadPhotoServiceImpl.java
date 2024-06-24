package com.travel.serviceImpl;


import java.io.IOException;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travel.bean.ApiResponse;
import com.travel.bean.UploadPhoto;
import com.travel.bean.util.ImageUtil;
import com.travel.repository.UploadPhotoRepository;
import com.travel.service.UploadPhotoService;

@Service
public class UploadPhotoServiceImpl implements UploadPhotoService {

	@Autowired
	UploadPhotoRepository photoRepository;

	@Override
  public ApiResponse uploadImage(MultipartFile file) throws IOException {
    	
    	ApiResponse apiResponse=new ApiResponse();
        UploadPhoto photo =	photoRepository.save(UploadPhoto.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        apiResponse.setStatus(true);
        apiResponse.setStatusCode("200");
        apiResponse.setMessage("upload image sucessfully");
        apiResponse.setData(photo);
        return apiResponse;
}


	@Override
	public ApiResponse getPhotoByname(String name) throws IOException {
	    ApiResponse apiResponse = new ApiResponse();
	    Optional<UploadPhoto> dbImage = photoRepository.findByImageName(name);
	    if (dbImage.isPresent()) {
	        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
	        apiResponse.setData(image);
	    } else {
	        // Handle case where image is not found
	        apiResponse.setMessage("Image not found for name: " + name);
	        apiResponse.setStatus(false);
	    }
	    return apiResponse;
	}

}