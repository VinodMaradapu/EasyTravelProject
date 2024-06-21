package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.bean.LocationRequest;

@Repository
public interface LocationRepository extends JpaRepository<LocationRequest, Integer>{

}
