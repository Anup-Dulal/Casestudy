package com.farmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.farmer.model.Payment;
import com.farmer.repo.PaymentRepo;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@KafkaListener(topics = "ThirdTopic", groupId = "group", containerFactory = "concurrent")
	public void publish (Payment payment) {
		paymentRepo.save(payment);
	}

	public Payment getPaymentDetails(String name) {
		return paymentRepo.findByFarmerName(name);
	}

}
