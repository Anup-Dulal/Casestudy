package com.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.Repo.PaymentRepo;
import com.payment.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepo paymentRepo;

	@Override
	public Payment addPaymentDetails(Payment payment) {
		return paymentRepo.save(payment);	
	}

	@Override
	public Payment getPaymentByDealerName(String name) {
		return paymentRepo.findByDealerName(name);
	}

}
