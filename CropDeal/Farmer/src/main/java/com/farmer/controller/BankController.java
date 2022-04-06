package com.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.model.BankDetails;
import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.BankService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping("/farmer/addaccount")
	public BankDetails saveBankDetails(@RequestBody BankDetails details) {
		return bankService.saveBankDetails(details);
	}

	@GetMapping("/farmer/getAccount")
	public List<BankDetails> getBankDetails() {
		return bankService.getBankDetails();
	}

	@GetMapping("/bank/{userName}")
	public BankDetails getBankByUserName(@PathVariable("userName") String name) throws BANKACCOUNTNOTFOUND {
		return bankService.getBankByName(name);
	}

}
