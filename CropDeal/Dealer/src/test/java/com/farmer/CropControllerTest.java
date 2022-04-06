package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmer.controller.CropsController;
import com.farmer.model.CropDetails;
import com.farmer.service.CropNotFound;
import com.farmer.service.CropService;

@SpringBootTest
class CropControllerTest {

	@InjectMocks
	CropsController controller;
	
	@Mock
	CropService cropService;
	
	@Test
	void getCropList() {
		CropDetails cropDetails = new CropDetails(1, "rice", 3L, "Delhi", "Ram", 22334L);
		CropDetails cropDetails1 = new CropDetails(2, "rice", 3L, "Delhi", "Ram", 22334L);
		CropDetails cropDetails2 = new CropDetails();
		cropDetails2.setId(3);
		cropDetails2.setCropType("rice");
		cropDetails2.setFarmerName("Hari");
		cropDetails2.setLocation("Delhi");
		cropDetails2.setQuantity(3L);
		cropDetails2.setPhoneNo(1122L);
		List<CropDetails> cropDetails3 = new ArrayList<CropDetails>();
		cropDetails3.add(cropDetails2);
		cropDetails3.add(cropDetails1);
		cropDetails3.add(cropDetails);
		when(cropService.getCropDetails()).thenReturn(cropDetails3);
		List<CropDetails> details = controller.getCropDetails();
		assertEquals(3, details.size());
	}
	
	@Test
	void getCropById() throws CropNotFound {
		Optional<CropDetails> cropDetails = Optional.of(new CropDetails(1, "rice", 3L, "Delhi", "Ram", 22334L));
		when(cropService.getCropDetailsById(1)).thenReturn(cropDetails);
		Optional<CropDetails> cropDetails2 = controller.getCropById(1);
		assertEquals(1, cropDetails2.get().getId());
		assertEquals("rice", cropDetails2.get().getCropType());
		assertEquals(3L, cropDetails2.get().getQuantity());
		assertEquals("Delhi", cropDetails2.get().getLocation());
		assertEquals("Ram", cropDetails2.get().getFarmerName());
		assertEquals(22334L, cropDetails2.get().getPhoneNo());
	}

}
