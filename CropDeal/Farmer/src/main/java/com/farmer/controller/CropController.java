package com.farmer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.model.CropDetails;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.CropService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CropController {
	@Autowired
	CropService cropService;

	@Autowired
	KafkaTemplate<String, CropDetails> kafkaTemplate;

	private static final String TOPIC = "NewTopic";

	@PostMapping("/addcrop")
	public CropDetails saveCropDetails(@RequestBody CropDetails cropDetails) {
		kafkaTemplate.send(TOPIC, cropDetails);
		return cropService.saveCropdetails(cropDetails);
	}

	@GetMapping("/getcrop")
	public List<CropDetails> getCropDetails() {
		return cropService.getCropDetails();
	}

	@GetMapping("/getCrop/{id}")
	public Optional<CropDetails> getCropById(@PathVariable("id") int id) throws CROPDETAILSNOTFOUND {
		return cropService.getCropById(id);
	}
	
	@PutMapping("/updatecropdetails/{id}")
	public CropDetails updateCropDetails(@RequestBody CropDetails cropDetails, @PathVariable("id") int id) throws CROPDETAILSNOTFOUND {
		return cropService.updateCropDetails(cropDetails,id);
	}
	
	@DeleteMapping("/deleteCrop/{id}")
	public void deleteCropDetails(@PathVariable("id") int id) {
		cropService.deleteCropDetails(id);
	}
	
	@GetMapping("/getCropName/{name}")
	public CropDetails getCropDetailsByName(@PathVariable("name") String name) {
		return cropService.getCropByName(name);
	}

}
