package com.travel.bean;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="booking_data")
public class BookingDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_location")
    private String from;
    
    @Column(name = "captain_name")
    private String captainName; 
    
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "to_location")
    private String to;

    @Column(name = "distance")
    private double distance;

    @Column(name = "payment")
    private long payment;

    @Column(name = "time")
    private LocalDateTime time;
}
