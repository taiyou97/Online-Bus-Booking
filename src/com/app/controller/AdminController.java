package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Bus;
import com.app.pojos.Routes;
import com.app.service.IBusService;
import com.app.service.IRouteService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IRouteService routeService;
	@Autowired
	IBusService busService;
	
	@PostConstruct
	public void init() {
		System.out.println("AdminController()");
	}
	
	
	//Routes Services
	@GetMapping("/routes")
	public ResponseEntity<?> getAllRoutes() {
		System.out.println("getAllRoutes");
		try {
			return new ResponseEntity<List<Routes>>(routeService.getAllRoutes(), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot get", HttpStatus.OK);
		}
	}
	
	@GetMapping("/routes/{id}")
	public ResponseEntity<?> getRoutesById(@PathVariable int id) {
		System.out.println("getRoutesById");
		try {
			return new ResponseEntity<Routes>(routeService.getRouteById(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot get", HttpStatus.OK);
		}
	}
	
	@PostMapping("/routes")
	public ResponseEntity<?> addRoutes(@RequestBody Routes r) {
		System.out.println("addRoutes");
		try {
			return new ResponseEntity<String>(routeService.addRoutes(r), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot be added successfully", HttpStatus.OK);
		}
	}
	
	@PutMapping("/routes")
	public ResponseEntity<?> updateRoutes(@RequestBody Routes r) {
		System.out.println("updateRoutes");
		try {
			HttpHeaders hd = new HttpHeaders();
			hd.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<String>(routeService.updateRoutes(r), hd, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot be added successfully", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/routes/{id}")
	public ResponseEntity<?> removeRoutes(@PathVariable int id) {
		System.out.println("removeRoutes");
		try {
			return new ResponseEntity<String>(routeService.removeRoutes(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot be removed", HttpStatus.OK);
		}
	}
	
	
	//Bus Services
	@GetMapping("/bus")
	public ResponseEntity<?> getAllBuses() {
		System.out.println("getAllBuses");
		try {
			return new ResponseEntity<List<Bus>>(busService.getAllBus(), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();	
			return new ResponseEntity<String>("Bus cannot get", HttpStatus.OK);
		}
	}
	
	@GetMapping("/bus/{id}")
	public ResponseEntity<?> getBusById(@PathVariable int id) {
		System.out.println("getBusById");
		try {
			return new ResponseEntity<Bus>(busService.getBusById(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot get", HttpStatus.OK);
		}
	}
	
	@PostMapping("/bus/{routeId}")
	public ResponseEntity<?> addBus(@RequestBody Bus b, @PathVariable int routeId) {
		System.out.println("addBus");
		try {
			System.out.println(b);
			return new ResponseEntity<String>(busService.addBus(b, routeId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be added successfully", HttpStatus.OK);
		}
	}
	
	@PutMapping("/bus")
	public ResponseEntity<?> updateBus(@RequestBody Bus b) {
		System.out.println("updateBus");
		try {
			busService.updateBus(b);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be added successfully" ,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/bus/{id}")
	public ResponseEntity<?> removeBus(@PathVariable int id) {
		System.out.println("removeBus");
		try {
			return new ResponseEntity<String>(busService.removeBus(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be removed", HttpStatus.OK);
		}
	}
	
}
