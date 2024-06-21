package com.travel.service;

import com.travel.bean.ApiResponse;
import com.travel.bean.LocationRequest;

public interface LocationService {

	ApiResponse distanceCalculator(LocationRequest locationRequest);

}
