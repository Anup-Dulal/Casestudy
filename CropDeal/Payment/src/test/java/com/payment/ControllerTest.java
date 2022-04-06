package com.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.payment.PaymentController.PaymentController;
import com.payment.model.Payment;
import com.payment.service.PaymentService;
@SpringBootTest
class ControllerTest {
	
	@InjectMocks
	PaymentController controller;
	
	@Mock
	PaymentService paymentService;
	
	@Mock
	KafkaTemplate<String, Payment> kafkaTemplate;
	
	@Test
	void addPaymentTest() {
		Payment payment = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		kafkaTemplate.send("TOPIC",payment);
		when(paymentService.addPaymentDetails(payment)).thenReturn(payment);
		Payment payment2 = controller.addPaymentDetails(payment);
		assertThat(payment2.getDate()).isEqualTo("12-09");
	}
	
	@Test
	void getPaymentByName() {
		Payment payment = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		when(paymentService.getPaymentByDealerName("Hari")).thenReturn(payment);
		Payment payment2 = controller.getPaymentByDealername("Hari");
		assertThat(payment2.getId()).isEqualTo(1L);
	}
	

	

}
