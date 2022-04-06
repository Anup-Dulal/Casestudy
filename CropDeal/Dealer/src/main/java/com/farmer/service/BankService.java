package com.farmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.BankDetails;
import com.farmer.repo.BankRepo;

@Service
public class BankService {
	
	@Autowired
	private BankRepo bankRepo;

	public BankDetails saveBankDetails(BankDetails bankDetails) {
		return bankRepo.save(bankDetails);
	}

	public BankDetails getBankAccountByName(String name) {
		if(bankRepo.findByUserName(name) != null) {
			return bankRepo.findByUserName(name);
		}else {
			throw new AccountNotFound();
		}
	}
	
	

}
