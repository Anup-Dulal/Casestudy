package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.BankDetails;

public interface BankRepo extends MongoRepository<BankDetails, String> {

	BankDetails findByUserName(String name);

}
