package com.payment.service;

import com.payment.model.Payment;

public interface PaymentService {

	Payment addPaymentDetails(Payment payment);

	Payment getPaymentByDealerName(String name);

}
