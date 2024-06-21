package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.ApiResponse;
import com.travel.bean.LocationRequest;
import com.travel.service.LocationService;

@RestController
public class LocationController {

	   @Autowired
	   LocationService locationService;
	
	@PutMapping("/location")
	public ApiResponse enableLocation(@RequestBody LocationRequest locationRequest) {
		return locationService.distanceCalculator(locationRequest);
	}
}
