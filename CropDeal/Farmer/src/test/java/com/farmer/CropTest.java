package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.CropDetails;
import com.farmer.repo.CropRepo;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.CropService;


@SpringBootTest
class CropTest {
	
	@Autowired
	private CropService cropService;
	
	@MockBean
	private CropRepo cropRepo;
	
	@Test
	void addCropDetails() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		when(cropRepo.save(cropDetails)).thenReturn(cropDetails);
		assertEquals(cropDetails, cropService.saveCropdetails(cropDetails));
		
		
	}
	
	@Test
	void getCrop() {
		when(cropRepo.findAll()).thenReturn(Stream.of(new CropDetails(1,"wheat",1123L,"USA","Hari",11223L)).collect(Collectors.toList()));
		assertEquals(1, cropService.getCropDetails().size());
	}
	
	@Test
	void getCropById() throws CROPDETAILSNOTFOUND {
		Optional<CropDetails> cropDetails = Optional.of(new CropDetails(1,"wheat",1123L,"USA","Hari",11223L)); 
		when(cropRepo.findById(1)).thenReturn(cropDetails);
		Optional<CropDetails> crop = cropService.getCropById(1);
		assertEquals(1123L, crop.get().getQuantity());
		assertEquals(1, crop.get().getId());
		
	}
	
	@Test
	void updateCrop() throws CROPDETAILSNOTFOUND {
		CropDetails cropDetails = new CropDetails(9,"wheat",1123L,"USA","Hari",11223L);
		assertThrows(CROPDETAILSNOTFOUND.class, ()->{
		when(cropRepo.findByFarmerName("Hari")).thenReturn(cropDetails);
		assertEquals(cropDetails,cropService.updateCropDetails(cropDetails, 9));
		});
		Optional<CropDetails> cropDetails1 = Optional.of(new CropDetails(9,"wheat",1123L,"USA","Hari",11223L));
		when(cropRepo.findById(9)).thenReturn(cropDetails1);
		if(cropDetails1.isPresent()) {
			CropDetails cropDetails2 = cropDetails1.get();
			cropDetails2.setId(cropDetails.getId());
			cropDetails2.setCropType(cropDetails.getCropType());
			when(cropRepo.save(cropDetails2)).thenReturn(cropDetails2);
			assertEquals(cropDetails2.getCropType(), cropService.updateCropDetails(cropDetails2, 9).getCropType());
		}
	}
	
	@Test
	void deleteCrop() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		cropService.deleteCropDetails(1);
		verify(cropRepo,times(1)).deleteById(1);
	}

	@Test
	void getCropByName() {
		CropDetails cropDetails = new CropDetails(1,"wheat",1123L,"USA","Hari",11223L);
		when(cropRepo.findByFarmerName("Hari")).thenReturn(cropDetails);
		CropDetails cropDetails2 = cropService.getCropByName("Hari");
		assertEquals("Hari", cropDetails2.getFarmerName());
	}

	

}
