package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.farmer.controller.HomeResource;
import com.farmer.model.FarmerInfo;
import com.farmer.model.Invoice;
import com.farmer.model.Payment;
import com.farmer.service.FARMERINFONOTFOUND;
import com.farmer.service.FarmerService;
import com.farmer.service.InvoiceService;
import com.farmer.service.PaymentService;

@SpringBootTest
class ControllerTest {
	
	@InjectMocks
	HomeResource homeResource;
	
	@Mock
	FarmerService farmerService;
	
	@Mock
	InvoiceService invoiceService;
	
	@Mock
	PaymentService paymentService;
	
	@Test
	void testAddFarmerInfo() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		
		when(farmerService.saveFarmerinfo(any(FarmerInfo.class))).thenReturn(new FarmerInfo(1,"smith","Delhi",112233L, "Delhi"));
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","abc@xyz.com",112233L, "Delhi");
		FarmerInfo farmerInfo2 = homeResource.saveFarmerinfo(farmerInfo);
		assertThat(farmerInfo2.getAddress()).isEqualTo("Delhi");
	}
	@Test
	void testgetFarmerInfo() {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","Delhi",112233L, "ABC@xyz.com");
		FarmerInfo farmerInfo1 = new FarmerInfo(2,"smith","Delhi",112233L, "ABC@xyz.com");
		FarmerInfo farmerInfo2 = new FarmerInfo(2,"smith","Delhi",112233L, "ABC@xyz.com");
		List<FarmerInfo> farmerInfos = new ArrayList<>();
		farmerInfos.add(farmerInfo);
		farmerInfos.add(farmerInfo1);
		farmerInfos.add(farmerInfo2);
		
		when(farmerService.getFarmer()).thenReturn(farmerInfos);
		
		List<FarmerInfo> info = homeResource.getFarmerinfo();
		assertEquals(3, info.size());
	}
	@Test
	void testgetFarmerInfoById() throws FARMERINFONOTFOUND {
		Optional<FarmerInfo> farmerInfo = Optional.of(new FarmerInfo(1,"smith","ABC@XYZ.com",112233L, "Delhi"));
		when(farmerService.getFamerInfoById(1)).thenReturn(farmerInfo);
		Optional<FarmerInfo> farmerInfo2 = homeResource.getFarmerInfoById(1);
		assertThat(farmerInfo2.get().getAddress()).isEqualTo("Delhi");
	}
	@Test
	void testgetFarmerInfoByName() throws FARMERINFONOTFOUND {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","Delhi@xyz.com",112233L, "Delhi");
		when(farmerService.getFarmerByName("smith")).thenReturn(farmerInfo);
		FarmerInfo farmerInfo2 = homeResource.getFarmerInfoByName("smith");
		assertThat(farmerInfo2.getAddress()).isEqualTo("Delhi");
	}
	@Test
	void testDeleteFarmerInfo() {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","Delhi",112233L, "ABC@xyz.com");
		homeResource.deleteFarmerIndo(1);
		verify(farmerService,times(1)).deleteFarmerInfo(1);
	}
	@Test
	void testUpdateFarmerInfo() throws FARMERINFONOTFOUND {
		FarmerInfo farmerInfo = new FarmerInfo(1,"smith","Delhi",112233L, "ABC@xyz.com");
		farmerInfo.setAddress("USA");
		when(farmerService.updateFarmerInfo(farmerInfo, 1)).thenReturn(farmerInfo);
		FarmerInfo farmerInfo2 = homeResource.updateFarmerInfo(1, farmerInfo);
		assertThat(farmerInfo2.getAddress()).isEqualTo("USA");
	}
	
	@Test
	void testGetInvoiceByName() {
		Invoice invoice = new Invoice("1", "Hari", "Ram", "Rice", 112L, 300L, 400L);
		when(invoiceService.getInvoiceDetailsByName("Hari")).thenReturn(invoice);
		Invoice invoice2 = homeResource.getInvoiceDetailsByName("Hari");
		assertThat(invoice2.getCropName()).isEqualTo("Rice");
	}
	
	@Test
	void testGetPaymentByName() {
		Payment payment = new Payment(1L, 11L, "12-09", 123L, "Hari","Ram", 123L, 456L, 1234);
		when(paymentService.getPaymentDetails("Ram")).thenReturn(payment);
		Payment payment2 = homeResource.getPaymentDetailsByName("Ram");
		assertThat(payment2.getDealerName()).isEqualTo("Hari");
	}
	
}
