package com.farmer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.model.CropDetails;
import com.farmer.repo.CropRepo;
import com.farmer.service.CropNotFound;
import com.farmer.service.CropService;

@SpringBootTest
class CropTest {
	
	@Autowired
	private CropService cropService;
	
	@MockBean
	private CropRepo cropRepo;
	
	
	@Test
	void getCropDetailTest() {
		when(cropRepo.findAll()).thenReturn(Stream.of(new CropDetails(1, "rice", 3L, "Delhi", "Ram", 22334L)).collect(Collectors.toList()));
		assertEquals(1, cropService.getCropDetails().size());
	}
	
	@Test
	void getCropDetailsByIdTest() throws CropNotFound {
		Optional<CropDetails> cropDetails = Optional.of(new CropDetails(1, "rice", 3L, "Delhi", "Ram", 22334L));
		when(cropRepo.findById(1)).thenReturn(cropDetails);
		Optional<CropDetails> crop = cropService.getCropDetailsById(1);
		assertEquals("rice", crop.get().getCropType());
	}

	

}
