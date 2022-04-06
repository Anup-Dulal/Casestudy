package com.farmer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.model.CropDetails;
import com.farmer.service.CropNotFound;
import com.farmer.service.CropService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CropsController {
	
	
	@Autowired
	private CropService cropService;
	
	
	
	@GetMapping("/crop/getcrop")
	public List<CropDetails> getCropDetails() {
		return cropService.getCropDetails();
	}
	
	@GetMapping("/crop/getcrop/{id}")
	public Optional<CropDetails> getCropById(@PathVariable("id") int id) throws CropNotFound {
		return cropService.getCropDetailsById(id);
	}

}
