package com.farmer;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.farmer.Exceptions.DealerExceptionClass;
import com.farmer.service.AccountNotFound;
import com.farmer.service.BankService;
import com.farmer.service.CropNotFound;
import com.farmer.service.CropService;
import com.farmer.service.DealerService;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.InvoiceService;
import com.farmer.service.USERNOTFOUND;

@SpringBootTest
class ExceptionClassTest {
	
	@InjectMocks
	DealerExceptionClass class1;
	
	@Mock
	USERNOTFOUND usernotfound;
	
	@Mock
	AccountNotFound accountNotFound;
	
	@Mock
	CropNotFound cropNotFound;
	
	@Mock
	InvoiceNotFound notFound;
	
	@Mock
	DealerService dealerService;
	
	@Mock
	InvoiceService invoiceService;
	
	@Mock
	CropService cropService;
	
	@Mock
	BankService bankService;
	
	@Test
	void userNotFound() {
		assertThrows(class1.userNotFoundException(usernotfound),()->{
			if(dealerService.getDealerById(2).isEmpty());
		});
	}
	
	@Test
	void bankNotFound() {
		assertThrows(class1.accountNotFound(accountNotFound), ()->{
			if(bankService.getBankAccountByName("Hari") != null);
		});
	}

	@Test
	void invoiceNotFound() {
		assertThrows(class1.invoiceNotFound(notFound), ()->{
			if(invoiceService.getInvoiceByName("Ram") != null);
		});
	}
	@Test
	void cropNotFound() {
		assertThrows(class1.cropDetailsNotFound(cropNotFound), ()->{
			if(cropService.getCropDetailsById(2).isEmpty());
		});
	}
	private void assertThrows(ResponseEntity userNotFoundException, Executable executable) {
		// TODO Auto-generated method stub
		
	}

	

}
