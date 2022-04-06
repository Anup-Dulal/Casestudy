package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.CropDetails;
import com.admin.service.CropService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CropController {
	
	
	@Autowired
	private CropService cropService;
	
	
	@PostMapping("/crop/add")
	public CropDetails addCropDetails(@RequestBody CropDetails cropDetails) {
		return cropService.addCropDetails(cropDetails);
	}
	
	@GetMapping("/crop/get")
	public List<CropDetails> getCar() {
		return cropService.getCrop();
	}
	@GetMapping("/crop/get/{id}")
	public CropDetails getCropById(@PathVariable("id") int id){
		return cropService.getCropById(id);
	}
	@PutMapping("/crop/update/{id}")
	public void updateCrop(@PathVariable("id") int id, @RequestBody CropDetails crop)  {
		cropService.updateCrop(id,crop);
	}
	
	@DeleteMapping("/crop/delete/{id}")
	public void deleteCrop(@PathVariable("id") int id) {
		cropService.deleteCrop(id);
	}

}
