package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.User;
import com.admin.model.UserModel;
import com.admin.service.SequenceGeneratorService;
import com.admin.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class HomeController {
	
	
	@Autowired
	private UserService service;
	
	@Autowired
	private SequenceGeneratorService service2;
	
	
	
	@GetMapping("/login")
	public List<User> getUserDetails() {
		return service.getUserDetails();
	}
	
	@PostMapping("/register")
	public void addUserDetails( @RequestBody UserModel model) {
		model.setId(service2.getSequenceNumber(model.SEQUENCE_NAME));
		 service.registerUser(model);
	}
	
	@GetMapping("/welcome/all")
	public String welcome() {
		return "Welcome";
	}
	
	@GetMapping("/get/{userName}")
	public User getUserByUserName(@PathVariable("userName") String name) {
		return service.getUserByName(name);
	}
	
}
