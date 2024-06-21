package com.travel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.maps.errors.ApiException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.travel.bean.CityCoordinates;
import com.travel.service.CityCoordinatesService;

@RestController
public class CityCoordinatesController {

    @Autowired
    private CityCoordinatesService cityCoordinatesService;

    @Value("${google.maps.api.key}") // Assuming you have configured your Google Maps API key in application.properties
    private String googleMapsApiKey;

    @PostMapping("/coordinates")
    public String saveCityCoordinates(@RequestBody String cityName) {
        double[] coordinates = fetchCoordinates(cityName);
        if (coordinates != null && coordinates.length == 2) {
            CityCoordinates cityCoordinates = new CityCoordinates();
            cityCoordinates.setCityName(cityName);
            cityCoordinates.setLatitude(coordinates[0]);
            cityCoordinates.setLongitude(coordinates[1]);
            cityCoordinatesService.saveCityCoordinates(cityCoordinates);
            return "Coordinates for " + cityName + " saved successfully.";
        } else {
            return "Failed to fetch coordinates for " + cityName;
        }
    }

    private double[] fetchCoordinates(String cityName) {
    	try {
    	    GeoApiContext context = new GeoApiContext.Builder().apiKey(googleMapsApiKey).build();
    	    GeocodingResult[] results = GeocodingApi.geocode(context, cityName).await();
    	    if (results != null && results.length > 0) {
    	        double latitude = results[0].geometry.location.lat;
    	        double longitude = results[0].geometry.location.lng;
    	        return new double[]{latitude, longitude};
    	    }
    	} catch (ApiException | InterruptedException | IOException e) {
    	    e.printStackTrace();
    	    // Log the error or return a specific error message indicating invalid API key
    	    throw new IllegalStateException("Invalid API key.");
    	}
        return null;
    }
}
