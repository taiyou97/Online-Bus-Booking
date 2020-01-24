package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.CustomerRoleType;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	IUserService service;
	
	@PostConstruct
	public void init() {
		System.out.println("UserController()");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User u, HttpSession hs) {
		System.out.println("validateUser" + u.getEmail() + u.getPassword());
		try {
			u = service.validateUser(u.getEmail(), u.getPassword());
			//Http Session
			if( u.getRole().equals(CustomerRoleType.ADMIN) )
				hs.setAttribute("adminDetails", u);
			else if( u.getRole().equals(CustomerRoleType.CUSTOMER) )
				hs.setAttribute("userDetails", u);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}catch (RuntimeException e) {
			//e.printStackTrace();
			return new ResponseEntity<String>("User Invalid", HttpStatus.OK);
		}
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestBody String email) {
		System.out.println("getUser" + email );
		try {
			User u = service.getUser(email);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}catch (RuntimeException e) {
			//e.printStackTrace();
			return new ResponseEntity<String>("User Invalid", HttpStatus.OK);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u) {
		System.out.println("registerUser()");
		try {
			service.registerUser(u);
			return new ResponseEntity<String>("Successfully Registered", HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Not Registered", HttpStatus.OK);
		}
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody User u) {
		System.out.println("changePassword()");
		try {
			service.updatePassword(u.getEmail(), u.getPassword());
			/*
			if( hs!=null )
				hs.invalidate();
			HttpHeaders hd = new HttpHeaders();
			//Add Login/Home URL
			hd.add("refresh", "5;url=https://www.baeldung.com/spring-response-entity");
			*/
			return new ResponseEntity<String>("Password changed successfully, loginAgain", HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Change Password", HttpStatus.OK);
		}
	}
	
	@PutMapping("/changePhone")
	public ResponseEntity<?> changePhone(@RequestBody User u) {
		System.out.println("changePhone()");
		try {
			service.updatePhone(u.getEmail(), u.getPhone());
			return new ResponseEntity<String>("Phone Number changed Successfully", HttpStatus.OK);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Phone cannot be changed", HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User u) {
		System.out.println("updateUser()");
		try {
			service.updateUser(u);
			return new ResponseEntity<String>("User updated Successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("User cannot be updated", HttpStatus.OK);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> invalidateUser(HttpSession hs) {
		if(hs!=null)
			hs.invalidate();
		HttpHeaders hd = new HttpHeaders();
		//Add Login/Home URL
		hd.add("refresh", "5;url=https://www.baeldung.com/spring-response-entity");
		return new ResponseEntity<String>("You are successfully LogOut", hd, HttpStatus.OK);
	}
}
