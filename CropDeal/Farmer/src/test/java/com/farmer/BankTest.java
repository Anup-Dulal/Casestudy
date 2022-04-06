package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.BankDetails;
import com.farmer.repo.BankRepo;
import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.BankService;

@SpringBootTest
class BankTest {

	@Autowired
	private BankService bankService;

	@MockBean
	private BankRepo bankRepo;

	@Test
	void addBankDetails() {
		BankDetails bankDetails = new BankDetails();
		bankDetails.setId(1);
		bankDetails.setBank("ABC");
		bankDetails.setAccountno(1122L);
		bankDetails.setUserName("Ram");
		when(bankRepo.save(bankDetails)).thenReturn(bankDetails);
		assertEquals(bankDetails, bankService.saveBankDetails(bankDetails));
	}

	@Test
	void getBankDetails() {
		when(bankRepo.findAll())
				.thenReturn(Stream.of(new BankDetails(1, 112233L, "Ram", "ABC")).collect(Collectors.toList()));
		assertEquals(1, bankService.getBankDetails().size());
	}

	@Test
	void getBankDetailsByUserName() throws BANKACCOUNTNOTFOUND {
		BankDetails bankDetails = new BankDetails(1, 11223L, "Hari", "HBC");
		when(bankRepo.findByUserName("Hari")).thenReturn(bankDetails);
		BankDetails bankDetails2 = bankService.getBankByName("Hari");
		assertEquals(11223L, bankDetails2.getAccountno());
		assertEquals(1, bankDetails2.getId());

	}

}
