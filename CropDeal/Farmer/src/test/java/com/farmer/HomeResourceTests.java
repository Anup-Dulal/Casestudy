package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.farmer.model.FarmerInfo;
import com.farmer.model.Invoice;
import com.farmer.model.Payment;
import com.farmer.repo.FarmerRepo;
import com.farmer.repo.InvoiceRepo;
import com.farmer.repo.PaymentRepo;
import com.farmer.service.FARMERINFONOTFOUND;
import com.farmer.service.FarmerService;
import com.farmer.service.InvoiceService;
import com.farmer.service.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class HomeResourceTests {

	@Autowired
	private FarmerService farmerService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@MockBean
	private FarmerRepo farmerRepo;
	
	@MockBean
	private InvoiceRepo invoiceRepo;
	
	@MockBean
	private PaymentRepo paymentRepo;



	@Test
	void main(){
		FarmerApplication.main(new String[] {});
	}
	
		
	
	@Test
	void getFarmerInfo() {
		when(farmerRepo.findAll()).thenReturn(Stream.of(new FarmerInfo(1,"smith","Delhi",112233L, "ABC@xyz.com")).collect(Collectors.toList()));
		assertEquals(1, farmerService.getFarmer().size());
		
	}
	
	@Test
	void saveFarmerInfo() {
		FarmerInfo farmerInfo = new FarmerInfo();
		farmerInfo.setId(1);
		farmerInfo.setName("Hari");
		farmerInfo.setEmail("ABC@Xyz.com");
		farmerInfo.setAddress("Delhi");
		farmerInfo.setPhone(11223L);
		when(farmerRepo.save(farmerInfo)).thenReturn(farmerInfo);
		assertEquals(farmerInfo, farmerService.saveFarmerinfo(farmerInfo));
	}
	
	
	@Test
	void deleteFarmerInfo() {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","Delhi",112233L, "ABC@xyz.com");
		farmerService.deleteFarmerInfo(1);
		verify(farmerRepo,times(1)).deleteById(1);
	}
	
	@Test
	void getFarmerInfoByName() throws FARMERINFONOTFOUND {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","ABC@xyz.com",112233L, "Delhi");
		when(farmerRepo.findByName("smith")).thenReturn(farmerInfo);
		FarmerInfo farmerInfo2 = farmerService.getFarmerByName("smith");
		assertEquals("Delhi", farmerInfo2.getAddress());
		assertEquals(1, farmerInfo2.getId());
		assertEquals(112233L, farmerInfo2.getPhone());
	}
	
	@Test
	void getFarmerInfoById() throws FARMERINFONOTFOUND {
		Optional<FarmerInfo> farmerInfo = Optional.of(new FarmerInfo(1, "smith", "ABC@xyz.com", 112233L, "Delhi"));
		when(farmerRepo.findById(1)).thenReturn(farmerInfo);
		Optional<FarmerInfo> farmerInfo2 = farmerService.getFamerInfoById(1);
		assertEquals("smith", farmerInfo2.get().getName());
	}
	
	@Test
	void updateFarmerInfoById() throws FARMERINFONOTFOUND {
		FarmerInfo farmerInfo = new FarmerInfo(9,"smith","Abc@xyz.com",112233L, "Delhi");
		
		assertThrows(FARMERINFONOTFOUND.class, ()->{
			when(farmerRepo.findByName("smith")).thenReturn(farmerInfo);
			assertEquals(farmerInfo,farmerService.updateFarmerInfo(farmerInfo, 9));
		});
		Optional<FarmerInfo> farmerInfo1 = Optional.of(new FarmerInfo(9,"smith","Abc@xyz.com",112233L, "Delhi"));
		when(farmerRepo.findById(9)).thenReturn(farmerInfo1);
		if(farmerInfo1.isPresent()) {
			FarmerInfo farmerInfo2 = farmerInfo1.get();
			farmerInfo2.setId(farmerInfo.getId());
			farmerInfo2.setAddress(farmerInfo.getAddress());
			when(farmerRepo.save(farmerInfo2)).thenReturn(farmerInfo2);
			assertEquals(farmerInfo2.getAddress(), farmerService.updateFarmerInfo(farmerInfo2, 9).getAddress());
		}
		
	}
	
	@Test
	void getInvoiceByNameTest() {
		Invoice invoice = new Invoice("1", "Hari", "Ram", "Rice", 112L, 300L, 400L);
		Invoice invoice2 = new Invoice();
		invoice2.setCropName("wheat");
		invoice2.setDealerName("Hari");
		invoice2.setFarmerName("John");
		invoice2.setId("2");
		invoice2.setPrice(111L);
		invoice2.setQuantity(600L);
		invoice2.setTotal(900L);
		when(invoiceRepo.findByFarmerName("Ram")).thenReturn(invoice);
		Invoice invoice3 = invoiceService.getInvoiceDetailsByName("Ram");
		assertEquals("Rice", invoice3.getCropName());
		assertEquals("1", invoice3.getId());
		assertEquals("Hari", invoice3.getDealerName());
		assertEquals(112L, invoice3.getQuantity());
		assertEquals(300L, invoice3.getPrice());
		assertEquals(400L, invoice3.getTotal());
		assertEquals("Ram", invoice3.getFarmerName());
		
		
	}
	@Test
	void addInvoice() {
		Invoice invoice = new Invoice("1", "Hari", "Ram", "Rice", 112L, 300L, 400L);
		when(invoiceRepo.save(invoice)).thenReturn(invoice);
		invoiceService.publishInvoice(invoice);
		assertEquals("1", invoice.getId());
	}
	
	
	@Test
	void getPaymentByFarmerName() {
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
		Payment payment2 = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		when(paymentRepo.findByFarmerName("Ram")).thenReturn(payment);
		Payment payment3 = paymentService.getPaymentDetails("Ram");
		assertEquals("Ram", payment3.getFarmerName());
		assertEquals(1L, payment3.getId());
		assertEquals(1122L, payment3.getCardNo());
		assertEquals("12-09", payment3.getDate());
		assertEquals(11223L, payment3.getAmount());
		assertEquals(11223L, payment3.getFarmerAccountNo());
		assertEquals(119, payment3.getPinNo());
		assertEquals(112L, payment3.getCV());
	}
	@Test
	void savePaymentTest() {
		Payment payment = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		when(paymentRepo.save(payment)).thenReturn(payment);
		paymentService.publish(payment);
		assertEquals(1L, payment.getId());
	}

}
