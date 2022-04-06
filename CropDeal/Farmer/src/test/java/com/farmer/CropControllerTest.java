package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.farmer.controller.CropController;
import com.farmer.model.CropDetails;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.CropService;

@SpringBootTest
class CropControllerTest {

	@InjectMocks
	CropController controller;
	
	@Mock
	CropService cropService;
	
	@Mock
	KafkaTemplate<String,CropDetails> kafkaTemplate;
	
	@Test
	void addCropTest() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		kafkaTemplate.send("TOPIC",cropDetails);
		when(cropService.saveCropdetails(cropDetails)).thenReturn(cropDetails);
		CropDetails cropDetails2 = controller.saveCropDetails(cropDetails);
		assertThat(cropDetails2.getFarmerName()).isEqualTo("Hari");
	}
	@Test
	void getCropTest() {
		CropDetails cropDetails = new CropDetails();
		cropDetails.setId(1);
		cropDetails.setCropType("rice");
		cropDetails.setQuantity(33L);
		cropDetails.setLocation("Delhi");
		cropDetails.setFarmerName("Ram");
		cropDetails.setPhoneNo(11223L);
		CropDetails cropDetails1 = new CropDetails(2,"wheat",1123L,"USA","Hari",11223L);
		List<CropDetails> details = new ArrayList<CropDetails>();
		details.add(cropDetails);
		details.add(cropDetails1);
		when(cropService.getCropDetails()).thenReturn(details);
		List<CropDetails> cropDetails2 = controller.getCropDetails();
		assertEquals(2, cropDetails2.size());
	}
	
	@Test
	void getCropById() throws CROPDETAILSNOTFOUND {
		Optional<CropDetails> cropDetails = Optional.of(new CropDetails(1,"wheat",1123L,"USA","Hari",11223L));
		when(cropService.getCropById(1)).thenReturn(cropDetails);
		Optional<CropDetails> cropDetails2 = controller.getCropById(1);
		assertThat(cropDetails2.get().getCropType()).isEqualTo("wheat");
	}
	@Test
	void updateCropTest() throws CROPDETAILSNOTFOUND {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		cropDetails.setCropType("rice");
		when(cropService.updateCropDetails(cropDetails, 1)).thenReturn(cropDetails);
		CropDetails cropDetails2 = controller.updateCropDetails(cropDetails, 1);
		assertThat(cropDetails2.getLocation()).isEqualTo("USA");
		
	}
	@Test
	void deleteCropTest() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		controller.deleteCropDetails(1);
		verify(cropService,times(1)).deleteCropDetails(1);
	}
	@Test
	void getCropByNameTest() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		when(cropService.getCropByName("Hari")).thenReturn(cropDetails);
		CropDetails cropDetails2 = controller.getCropDetailsByName("Hari");
		assertThat(cropDetails2.getPhoneNo()).isEqualTo(11223L);
	}

}
