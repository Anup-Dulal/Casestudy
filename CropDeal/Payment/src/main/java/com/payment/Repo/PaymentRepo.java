package com.payment.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.payment.model.Payment;

public interface PaymentRepo extends MongoRepository<Payment, Long> {

	Payment findByDealerName(String name);

}
