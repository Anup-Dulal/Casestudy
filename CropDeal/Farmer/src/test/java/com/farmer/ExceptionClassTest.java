package com.farmer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.farmer.Exceptions.FarmerExceptionClass;
import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.BankService;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.CropService;
import com.farmer.service.FARMERINFONOTFOUND;
import com.farmer.service.FarmerService;

@SpringBootTest
class ExceptionClassTest {
	
	@InjectMocks
	FarmerExceptionClass class1;
	
	@Mock
	BANKACCOUNTNOTFOUND bankaccountnotfound;
	
	@Mock
	FARMERINFONOTFOUND farmerinfonotfound;
	
	@Mock
	CROPDETAILSNOTFOUND cropdetailsnotfound;
	
	@Mock
	BankService bankService;
	
	@Mock
	FarmerService farmerService;
	
	@Mock
	CropService cropService;
	
	@Test
	void bankNotFoundTest() {
		assertThrows(class1.bankAccountNotFound(bankaccountnotfound),()->{
			if(bankService.getBankByName("Hari") != null);
		});
		
	}
	
	@Test
	void farmerNotFound() {
		assertThrows(class1.farmerInfoNotFound(farmerinfonotfound), ()->{
			if(farmerService.getFamerInfoById(1).isEmpty());
		});
	}
	
	@Test
	void CropNotFound() {
		assertThrows(class1.cropDetailsNotFound(cropdetailsnotfound), ()->{
			if(cropService.getCropById(1).isEmpty());
		});
	}


	private void assertThrows(ResponseEntity bankAccountNotFound2, Executable executable) {
		
	}

	
}
