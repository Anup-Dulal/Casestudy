package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmer.controller.BankController;
import com.farmer.model.BankDetails;
import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.BankService;

@SpringBootTest
class BankControllerTest {

	@InjectMocks
	BankController bankController;
	
	@Mock
	BankService bankService;
	
	@Test
	void testaddBank() {
		BankDetails bankDetails = new BankDetails(1, 11223L, "Hari", "HBC");
		when(bankService.saveBankDetails(bankDetails)).thenReturn(bankDetails);
		BankDetails bankDetails2 = bankController.saveBankDetails(bankDetails);
		assertThat(bankDetails2.getBank()).isEqualTo("HBC");
		
	}
	@Test
	void testGetBank() {
		BankDetails bankDetails = new BankDetails(1, 11223L, "Hari", "HBC");
		BankDetails bankDetails1 = new BankDetails(2, 11223L, "Hari", "HBC");
		List<BankDetails> bankDetails2 = new ArrayList<BankDetails>();
		bankDetails2.add(bankDetails1);
		bankDetails2.add(bankDetails);
		when(bankService.getBankDetails()).thenReturn(bankDetails2);
		List<BankDetails> details = bankController.getBankDetails();
		assertEquals(2, details.size());
	}
	@Test
	void testGetBankByName() throws BANKACCOUNTNOTFOUND {
		BankDetails bankDetails = new BankDetails(1, 11223L, "Hari", "HBC");
		when(bankService.getBankByName("Hari")).thenReturn(bankDetails);
		BankDetails  bankDetails2 = bankController.getBankByUserName("Hari");
		assertThat(bankDetails2.getUserName()).isEqualTo("Hari");
	}

}
