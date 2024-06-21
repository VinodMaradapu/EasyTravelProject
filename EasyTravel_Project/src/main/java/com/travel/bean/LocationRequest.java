package com.travel.bean;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class LocationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double latitude1;
	private double longitude1;
	private double latitude2;
	private double longitude2;
//	private double dLat;
//	private double dLon;
	private double distance;
	private double paymentForBike;
	private double paymentForCar;
	private double paymentForAuto;
	private double paymentForBus;
	
}
