package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.bean.CityCoordinates;

public interface CityCoordinatesRepository extends JpaRepository<CityCoordinates, Long>{

}
