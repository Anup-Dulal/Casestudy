package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.Invoice;
import com.farmer.repo.InvoiceRepo;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.InvoiceService;

@SpringBootTest
class InvoiceTest {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@MockBean
	private InvoiceRepo invoiceRepo;
	
	
	@Test
	void addInvoiceTest() {
		Invoice invoice = new Invoice();
		invoice.setId("1");
		invoice.setDealerName("ram");
		invoice.setFarmerName("Hari");
		invoice.setCropName("rice");
		invoice.setPrice(112L);
		invoice.setQuantity(2L);
		invoice.setTotal(900L);
		when(invoiceRepo.save(invoice)).thenReturn(invoice);
		assertEquals(invoice, invoiceService.addInvoice(invoice));
	}
	
	@Test
	void getInvoiceByName() throws InvoiceNotFound {
		Invoice invoice = new Invoice("1", "Ram", "Hari", "rice", 2L, 300L, 600L);
		when(invoiceRepo.findByDealerName("Ram")).thenReturn(invoice);
		Invoice invoice2 = invoiceService.getInvoiceByName("Ram");
		assertEquals("rice", invoice2.getCropName());
	}

	

}
