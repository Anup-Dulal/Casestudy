package com.farmer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.BankDetails;
import com.farmer.repo.BankRepo;
import com.farmer.service.BankService;

@SpringBootTest
class BankTest {
	
	@Autowired
	private BankService bankService;
	
	@MockBean
	private BankRepo bankRepo;
	
	@Test
	void addBankDetailsTest() {
		BankDetails details = new BankDetails();
		details.setId(1);
		details.setUserName("John");
		details.setAccountno(11223L);
		details.setBank("ABC");
		when(bankRepo.save(details)).thenReturn(details);
		assertEquals(details, bankService.saveBankDetails(details));
	}
	
	@Test
	void getBankDetailsByName() {
		BankDetails details = new BankDetails(1,1112233L,"John","ABC");
		when(bankRepo.findByUserName("John")).thenReturn(details);
		BankDetails service=bankService.getBankAccountByName("John");
		assertEquals("John", service.getUserName() );
		
		
	}

	

}
