package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Bus;
import com.app.pojos.Seats;
import com.app.service.IBusService;
import com.app.service.ISeatsService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	IBusService busService;
	
	@Autowired
	ISeatsService seatsService;
	
	@PostConstruct
	public void init() {
		System.out.println("CustomerController()");
	}
	
	
	//Bus Service
	
	@GetMapping("/searchBus")
	public ResponseEntity<?> searchBus(@RequestParam String source, @RequestParam String destination,  @RequestParam String date) {
		System.out.println("searchBus");
		try {
			return new ResponseEntity<List<Bus>>(busService.getBusByRoutes(source, destination, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be searched", HttpStatus.OK);
		}
	}
	
	
	//Seats Service
	@GetMapping("/viewSeats")
	public ResponseEntity<?> getSeatsByBus(@RequestParam String date, int busId) {
		System.out.println("getSeatsByBus");
		try {
			return new ResponseEntity<Seats>(seatsService.getSeatsByBusId(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be searched", HttpStatus.OK);
		}
	}
	
	@GetMapping("/bookSeats")
	public ResponseEntity<?> bookSeatsByBus(@RequestParam String date, int busId, int noOfSeats) {
		System.out.println("bookSeatsByBus");
		try {
			return new ResponseEntity<String>(seatsService.bookSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be booked", HttpStatus.OK);
		}
	}
	
	@GetMapping("/addSeats")
	public ResponseEntity<?> addSeatsByBus(@RequestParam String date, int busId, int noOfSeats) {
		System.out.println("addSeatsByBus");
		try {
			return new ResponseEntity<String>(seatsService.addSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be added", HttpStatus.OK);
		}
	}
	
	@GetMapping("/removeSeats")
	public ResponseEntity<?> removeSeatsByBus(@RequestParam String date, int busId) {
		System.out.println("removeSeatsByBus");
		try {
			return new ResponseEntity<String>(seatsService.removeSeatsByBus(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be removed", HttpStatus.OK);
		}
	}
	
	
	//Tickets Service
	//@GetMapping("/bookTicket")
}
