package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmer.controller.BankController;
import com.farmer.model.BankDetails;
import com.farmer.service.BankService;

@SpringBootTest
class BankControllerTest {

	@InjectMocks
	BankController bankController;
	
	@Mock
	BankService bankService;
	
	@Test
	void addBankTest() {
		when(bankService.saveBankDetails(any(BankDetails.class))).thenReturn(new BankDetails(1,1112233L,"John","ABC"));
		BankDetails details = new BankDetails(1,1112233L,"John","ABC");
		BankDetails bankDetails = bankController.addBankDetails(details);
		assertThat(bankDetails.getAccountno()).isEqualTo(1112233L);
		assertThat(bankDetails.getBank()).isEqualTo("ABC");
		assertThat(bankDetails.getId()).isEqualTo(1);
		assertThat(bankDetails.getUserName()).isEqualTo("John");
	}
	
	@Test
	void getBankByNameTest() {
		BankDetails details = new BankDetails(1,1112233L,"John","ABC");
		when(bankService.getBankAccountByName("John")).thenReturn(details);
		BankDetails bankDetails = bankController.getBankDetailsByName("John");
		assertThat(bankDetails.getBank()).isEqualTo("ABC");
	}

}
