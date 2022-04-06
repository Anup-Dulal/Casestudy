package com.farmer;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmer.model.BankDetails;
import com.farmer.model.CropDetails;
import com.farmer.model.Dealer;
import com.farmer.model.Invoice;
import com.farmer.service.AccountNotFound;
import com.farmer.service.BankService;
import com.farmer.service.CropNotFound;
import com.farmer.service.CropService;
import com.farmer.service.DealerService;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.InvoiceService;
import com.farmer.service.USERNOTFOUND;


@SpringBootTest
class ExceptionTest {
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CropService cropService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private BankService bankService;
	
	@Test
	void testUserNotFound() throws USERNOTFOUND {
		Dealer dealer = new Dealer();
		dealer.setId(1);
		dealer.setPhoneNo(11223L);
		assertThrows(USERNOTFOUND.class, ()->
		dealerService.getDealerById(2),
		()->"Dealer not found");
	}
	@Test
	void testCropNotFound() {
		CropDetails cropDetails = new CropDetails();
		cropDetails.setId(1);
		cropDetails.setPhoneNo(1122L);
		assertThrows(CropNotFound.class, ()->
		cropService.getCropDetailsById(2));
	}
	@Test
	void testInvoiceNotFound() {
		Invoice invoice = new Invoice();
		invoice.setId("1");
		invoice.setPrice(11223L);
		assertThrows(InvoiceNotFound.class, ()->
		invoiceService.getInvoiceByName("Hari"));
	}
	
	@Test
	void testBankNotFound() throws AccountNotFound {
		BankDetails bankDetails = new BankDetails();
		bankDetails.setId(1);
		assertThrows(AccountNotFound.class, ()->
		bankService.getBankAccountByName("Ram"),
		()->"Account Not Found");
	}
}
