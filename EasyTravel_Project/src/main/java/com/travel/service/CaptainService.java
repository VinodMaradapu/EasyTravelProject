package com.travel.service;

import com.travel.bean.ApiResponse;
import com.travel.bean.CaptainDto;

public interface CaptainService {

	ApiResponse registerCaptain(CaptainDto captain);

	ApiResponse getAllCaptains();

	ApiResponse getCaptainById(int id);

	ApiResponse deleteCaptain(int id);

	ApiResponse updateCaptain(CaptainDto customer);

}
