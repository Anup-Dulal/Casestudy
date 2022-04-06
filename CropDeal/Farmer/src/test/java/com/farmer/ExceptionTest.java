package com.farmer;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.BankDetails;
import com.farmer.model.CropDetails;
import com.farmer.model.FarmerInfo;
import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.BankService;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.CropService;
import com.farmer.service.FARMERINFONOTFOUND;
import com.farmer.service.FarmerService;

@SpringBootTest
class ExceptionTest {
	
	@Autowired
	private FarmerService farmerService;
	
	@Autowired
	private CropService cropService;
	
	@Autowired
	private BankService bankService;
	
	
	
	@Test
	void testFarmerNotFound() throws FARMERINFONOTFOUND {
		
		FarmerInfo farmerInfo = new FarmerInfo();
		farmerInfo.setId(1);
		farmerInfo.setPhone(112L);
		assertThrows(FARMERINFONOTFOUND.class, ()->
		 farmerService.getFamerInfoById(2),
		 ()-> "Farmer info not found");
		
		
	}
	@Test
	void testCropNotFound() {
		CropDetails cropDetails = new CropDetails();
		cropDetails.setId(1);
		assertThrows(CROPDETAILSNOTFOUND.class, ()->
		cropService.getCropById(2),
		()->"Crop Not Found");
	}
	@Test
	void testBankDetails() throws BANKACCOUNTNOTFOUND {
		BankDetails bankDetails = new BankDetails();
		bankDetails.setId(1);
		assertThrows(BANKACCOUNTNOTFOUND.class, ()->
		bankService.getBankByName("Hari"),
		()-> "Bank Not Found");
	}
	
	
}
