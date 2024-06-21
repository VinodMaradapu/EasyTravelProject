package com.travel.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.CityCoordinates;
import com.travel.repository.CityCoordinatesRepository;
import com.travel.service.CityCoordinatesService;

@Service
public class CityCoordinatesServiceImpl implements CityCoordinatesService{

	    @Autowired
	    private CityCoordinatesRepository repository;

	    public void saveCityCoordinates(CityCoordinates cityCoordinates) {
	        repository.save(cityCoordinates);
	    }
}
