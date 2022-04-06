package com.farmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.model.BankDetails;
import com.farmer.service.AccountNotFound;
import com.farmer.service.BankService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dealer")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PostMapping("/bank/add")
	public BankDetails addBankDetails(@RequestBody BankDetails bankDetails) {
		return bankService.saveBankDetails(bankDetails);
	}
	@GetMapping("/bank/{userName}")
	public BankDetails getBankDetailsByName(@PathVariable("userName") String name) throws AccountNotFound  {
		log.info("Inside bank get method");
		return bankService.getBankAccountByName(name);
	}

}
