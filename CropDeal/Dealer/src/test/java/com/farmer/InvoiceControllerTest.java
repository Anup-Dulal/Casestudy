package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.farmer.controller.InvoiceController;
import com.farmer.model.Invoice;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.InvoiceService;

@SpringBootTest
class InvoiceControllerTest {
	
	@InjectMocks
	InvoiceController controller;
	
	@Mock
	InvoiceService invoiceService;
	
	@Mock
	KafkaTemplate<String, Invoice> kafkaTemplate;
	
	@Test
	void addInvoiceTest() {
		when(invoiceService.addInvoice(any(Invoice.class))).thenReturn(new Invoice("1", "Ram", "Hari", "rice", 2L, 300L, 600L));
		Invoice invoice = new Invoice("1", "Ram", "Hari", "rice", 2L, 300L, 600L);
		kafkaTemplate.send("TOPIC",invoice);
		Invoice invoice2 = controller.addInvoice(invoice);
		assertThat(invoice2.getCropName()).isEqualTo("rice");
		assertThat(invoice2.getId()).isEqualTo("1");
		assertThat(invoice2.getDealerName()).isEqualTo("Ram");
		assertThat(invoice2.getFarmerName()).isEqualTo("Hari");
		assertThat(invoice2.getQuantity()).isEqualTo(2L);
		assertThat(invoice2.getPrice()).isEqualTo(300L);
		assertThat(invoice2.getTotal()).isEqualTo(600L);
	}
	
	@Test
	void getInvoiceTest() throws InvoiceNotFound {
		Invoice invoice = new Invoice("1", "Ram", "Hari", "rice", 2L, 300L, 600L);
		when(invoiceService.getInvoiceByName("Ram")).thenReturn(invoice);
		Invoice invoice2 = controller.getInvoiceByName("Ram");
		assertThat(invoice2.getCropName()).isEqualTo("rice");
		assertThat(invoice2.getId()).isEqualTo("1");
		assertThat(invoice2.getDealerName()).isEqualTo("Ram");
		assertThat(invoice2.getFarmerName()).isEqualTo("Hari");
		assertThat(invoice2.getQuantity()).isEqualTo(2L);
		assertThat(invoice2.getPrice()).isEqualTo(300L);
		assertThat(invoice2.getTotal()).isEqualTo(600L);
		
	}

	
}
