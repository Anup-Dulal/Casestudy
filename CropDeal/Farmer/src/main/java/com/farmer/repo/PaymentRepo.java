package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.Payment;

public interface PaymentRepo extends MongoRepository<Payment, Long>{

	Payment findByFarmerName(String name);

}
