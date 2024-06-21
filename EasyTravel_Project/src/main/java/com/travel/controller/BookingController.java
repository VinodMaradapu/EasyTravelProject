package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.bean.BookingDto;
import com.travel.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookingride")
	public BookingDto saveBooking(@RequestBody BookingDto bookingDto) {
		return bookingService.bookRide(bookingDto);
	}
}
