package com.farmer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.BankDetails;
import com.farmer.repo.BankRepo;

@Service
public class BankService {
	
	@Autowired
	BankRepo bankRepo;

	public BankDetails saveBankDetails(BankDetails details) {
		return bankRepo.save(details);
	}

	public List<BankDetails> getBankDetails() {
		return bankRepo.findAll();
	}

	public BankDetails getBankByName(String name) throws BANKACCOUNTNOTFOUND {
		if( bankRepo.findByUserName(name) != null) {
			return bankRepo.findByUserName(name); 
		}else {
			throw new BANKACCOUNTNOTFOUND();
		}
	}
	
	


}
