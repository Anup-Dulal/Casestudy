package com.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.payment.Repo.PaymentRepo;
import com.payment.model.Payment;
import com.payment.service.PaymentService;

@SpringBootTest
class PaymentApplicationTests {
	
	@Autowired
	private PaymentService paymentService;
	
	@MockBean
	private PaymentRepo paymentRepo;
	
	@Test
	void addPaymentTest() {
		Payment payment = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		when(paymentRepo.save(payment)).thenReturn(payment);
		assertEquals(payment, paymentService.addPaymentDetails(payment));
	}
	
	@Test
	void getPaymentByDealerName() {
		Payment payment = new Payment();
		payment.setId(1L);
		payment.setAmount(11223L);
		payment.setCardNo(1122L);
		payment.setCV(112L);
		payment.setDate("12-09");
		payment.setDealerName("Hari");
		payment.setFarmerAccountNo(11223L);
		payment.setFarmerName("Ram");
		payment.setPinNo(119);
		when(paymentRepo.findByDealerName("Hari")).thenReturn(payment);
		Payment payment2 = paymentService.getPaymentByDealerName("Hari");
		assertEquals("Hari", payment2.getDealerName());
		assertEquals("Ram", payment2.getFarmerName());
		assertEquals(1L, payment2.getId());
		assertEquals(1122L, payment2.getCardNo());
		assertEquals("12-09", payment2.getDate());
		assertEquals(11223L, payment2.getAmount());
		assertEquals(11223L, payment2.getFarmerAccountNo());
		assertEquals(119, payment2.getPinNo());
		assertEquals(112L, payment2.getCV());
	}

}
