package com.payment.PaymentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.model.Payment;
import com.payment.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PaymentController {
	
	@Autowired
	KafkaTemplate<String, Payment> kafkaTemplate;
	
	@Autowired
	private PaymentService paymentService;
	
	private static final String TOPIC = "ThirdTopic";
	
	@PostMapping("/add")
	public Payment addPaymentDetails(@RequestBody Payment payment) {
		kafkaTemplate.send(TOPIC,payment);
		return paymentService.addPaymentDetails(payment);
	}
	
	@GetMapping("/get/{userName}")
	public Payment getPaymentByDealername(@PathVariable("userName") String name) {
		return paymentService.getPaymentByDealerName(name);
	}
	
	
}
