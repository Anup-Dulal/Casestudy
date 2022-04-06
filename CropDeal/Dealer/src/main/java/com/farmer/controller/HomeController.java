 package com.farmer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.farmer.model.Dealer;
import com.farmer.service.DealerService;
import com.farmer.service.USERNOTFOUND;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/dealer")
public class HomeController {
	
	@Autowired
	private DealerService dealerService;
	
	
	
	
	@PostMapping("/add")
	public Dealer addDealerinfo(@RequestBody Dealer dealer) {
		log.info("Inside the post method");
		return dealerService.addDealerInfo(dealer);
	}
	
	@GetMapping("/get")
	public List<Dealer> getDealerInfo(){
		log.info("inside the get method");
		return dealerService.getDealerInfo();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Dealer> getDealerById(@PathVariable("id") int id) {
		return dealerService.getDealerById(id);
	}
	@GetMapping("/getInfo/{userName}")
	public Dealer getDealerByName(@PathVariable("userName") String name) {
		return dealerService.getDealerByName(name);
	}
	
	@PutMapping("/update/{id}")
	public Dealer updateDealerinfo(@RequestBody Dealer dealer, @PathVariable("id") int id ) throws USERNOTFOUND {
		log.info("Inside the put method");
		return dealerService.updateDealerInfo(dealer,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteDealer(@PathVariable int id) throws USERNOTFOUND {
		log.info("Inside the delete method");
		dealerService.deleteDealer(id);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

}
