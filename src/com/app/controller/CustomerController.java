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
import com.app.service.IBusService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	IBusService busService;
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
			return new ResponseEntity<String>("Bus cannot be searched", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Seats Service
	@GetMapping("/viewSeats")
	public ResponseEntity<?> getSeatsOfBus(@RequestParam String date, int busId) {
		System.out.println("getSeatsOfBus");
		try {
			return null;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be searched", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Tickets Service
}
