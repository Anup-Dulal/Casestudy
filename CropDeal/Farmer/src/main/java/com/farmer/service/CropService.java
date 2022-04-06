package com.farmer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.CropDetails;
import com.farmer.repo.CropRepo;

@Service
public class CropService {
	
	@Autowired
	private CropRepo cropRepo;
	
	public CropDetails saveCropdetails(CropDetails cropDetails) {
		return cropRepo.save(cropDetails);
	}

	public List<CropDetails> getCropDetails() {
		return cropRepo.findAll();
	}

	public Optional<CropDetails> getCropById(int id) throws CROPDETAILSNOTFOUND {
		if(cropRepo.findById(id).isEmpty()) {
			throw new CROPDETAILSNOTFOUND();
		}else {
			return cropRepo.findById(id);
		}
		
	}

	public CropDetails updateCropDetails(CropDetails cropDetails, int id) throws CROPDETAILSNOTFOUND {
		Optional<CropDetails> cropDetails2 = cropRepo.findById(id);
		if(cropDetails2.isPresent()) {
			CropDetails details = cropDetails2.get();
			details.setId(cropDetails.getId());
			details.setFarmerName(cropDetails.getFarmerName());
			details.setCropType(cropDetails.getCropType());
			details.setLocation(cropDetails.getLocation());
			details.setPhoneNo(cropDetails.getPhoneNo());
			details.setQuantity(cropDetails.getQuantity());
			cropRepo.save(details);
			return details;
			
		}else {
			throw new CROPDETAILSNOTFOUND();
		}
	}

	public void deleteCropDetails(int id) {
		cropRepo.deleteById(id);
	}

	public CropDetails getCropByName(String name) {
		return cropRepo.findByFarmerName(name);
	}

}
