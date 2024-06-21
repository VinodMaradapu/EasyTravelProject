package com.travel.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.ApiResponse;
import com.travel.bean.LocationRequest;
import com.travel.repository.LocationRepository;
import com.travel.service.LocationService;
@Service
public class LocationServiceImpl implements LocationService{


	@Autowired
	LocationRepository locationRepository;
	
	@Override
	public ApiResponse distanceCalculator(LocationRequest locationRequest) {
		ApiResponse apiResponse = new ApiResponse();
        final double EARTH_RADIUS = 6371;
        double ratePerKilometerForBike = 20.0; 
        double ratePerKilometerForAuto = 25.0; 
        double ratePerKilometerForCar  = 35.0; 
        double ratePerKilometerForBus  = 40.0; 
        try {
        double latitude1 = Math.toRadians(locationRequest.getLatitude1());
        double longitude1 = Math.toRadians(locationRequest.getLongitude1());
        double latitude2 = Math.toRadians(locationRequest.getLatitude2());
        double longitude2 = Math.toRadians(locationRequest.getLongitude2());
        double dLat = latitude2 - latitude1;
        double dLon = longitude2 - longitude1;

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        // Create a new LocationData objeLocationRequestct to store in the database
        LocationRequest locationData = new LocationRequest();
        locationData.setLatitude1(locationRequest.getLatitude1());
        locationData.setLongitude1(locationRequest.getLongitude1());
        locationData.setLatitude2(locationRequest.getLatitude2());
        locationData.setLongitude2(locationRequest.getLongitude2());
//        locationData.setDLat(dLat);
//        locationData.setDLon(dLon);
        long roundedDistance = Math.round(distance);
        locationData.setDistance(roundedDistance);
        double paymentForBike = roundedDistance * ratePerKilometerForBike;
        locationData.setPaymentForBike(paymentForBike);
        double paymentForAuto = roundedDistance * ratePerKilometerForAuto;
        locationData.setPaymentForAuto(paymentForAuto);
        double paymentForCar = roundedDistance * ratePerKilometerForCar;
        locationData.setPaymentForCar(paymentForCar);
        double paymentForBus = roundedDistance * ratePerKilometerForBus;
        locationData.setPaymentForBus(paymentForBus);

            locationRepository.save(locationData);
            apiResponse.setStatus(true);
            apiResponse.setStatusCode("200");
            apiResponse.setMessage("distance calculated successfully");
            apiResponse.setData(locationData);
        	return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setStatus(false);
            apiResponse.setStatusCode("500");
            apiResponse.setMessage("Error occurred while saving data: " + e.getMessage());
        	return apiResponse;
        }
	}
}
