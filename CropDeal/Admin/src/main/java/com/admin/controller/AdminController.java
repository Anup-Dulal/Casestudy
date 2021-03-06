package com.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.FarmerInfo;
import com.admin.service.AdminNotFound;
import com.admin.service.AdminService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
	

	
	@Autowired
	private AdminService adminService;
	
	
	
	
	@GetMapping("/hello")
	public String sayHello() {
		log.info("Inside hello");
		return "HELLO";
	}
	@GetMapping("/get/{userName}")
	public Admin getAdminDetails(@PathVariable("userName") String name) throws AdminNotFound {
		log.info("Inside Admin Get Method");
		return adminService.getAdminDetails(name);
	}
	
	@PostMapping("/add")
	public void addAdmin(@RequestBody Admin admin) {
		adminService.addAdmin(admin);
	}
	
	@GetMapping("/dealer/get")
	@HystrixCommand(fallbackMethod = "getFallBackDealer")
	public List<Dealer> getDealerDetails() {
		return adminService.getDealerDetails();
	}
	
	@GetMapping("/farmer/get")
	@HystrixCommand(fallbackMethod = "getFallBackFarmer")
	public List<FarmerInfo> getFarmerInfo(){
		return adminService.getFarmerInfoDetails();
	}
	@GetMapping("/farmer/get/{id}")
	public FarmerInfo getFarmerById(@PathVariable("id") int id) {
		return adminService.getFarmerById(id);
	}
	
	@GetMapping("/dealer/get/{id}")
	public Dealer getDealerById(@PathVariable("id") int id) {
		return adminService.getDealerById(id);
	}
	@PostMapping("/dealer/add")
	public Dealer addDealer(@RequestBody Dealer dealer) {
		return adminService.addDealer(dealer);
	}
	
	@PutMapping("/farmer/update/{id}")
	public void editFarmerInfo(@PathVariable("id") int id, @RequestBody FarmerInfo farmerInfo) {
		adminService.updateFarmerInfo(id,farmerInfo);
	}
	
	@PutMapping("/dealer/update/{id}")
	public void editDealerInfo(@PathVariable("id") int id,@RequestBody Dealer dealer) {
		adminService.updateDealerInfo(id,dealer);
	}
	@DeleteMapping("/farmer/delete/{id}")
	public void deleteFarmer(@PathVariable("id") int id) {
		adminService.deleteFarmerById(id);
	}
	
	@DeleteMapping("/dealer/delete/{id}")
	public void deleteDealer(@PathVariable("id") int id) {
		adminService.deleteDealerById(id);
	}
	
	
	public List<FarmerInfo> getFallBackFarmer(){
		return Arrays.asList(new FarmerInfo(1,"No Name",0L,""));
	}
	
	public List<Dealer> getFallBackDealer() {
		return Arrays.asList(new Dealer(1, "", "", 0L));
	}

}
