package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.model.CropDetails;
import com.admin.repo.CropRepo;

@Service
public class CropServiceImpl implements CropService {
	
	@Autowired
	private CropRepo cropRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Override
	public CropDetails addCropDetails(CropDetails cropDetails) {
		CropDetails cropDetails2 = new CropDetails();
		cropDetails2.setId(cropDetails.getId());
		cropDetails2.setFarmerName(cropDetails.getFarmerName());
		cropDetails2.setCropType(cropDetails.getCropType());
		cropDetails2.setLocation(cropDetails.getLocation());
		cropDetails2.setPhoneNo(cropDetails.getPhoneNo());
		cropDetails2.setQuantity(cropDetails.getQuantity());
		return restTemplate.postForObject("http://farmer/addcrop", cropDetails2, CropDetails.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CropDetails> getCrop() {
		return restTemplate.getForObject("http://farmer/getcrop", List.class);
		
		
	}


	@Override
	public CropDetails getCropById(int id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		return restTemplate.getForObject("http://farmer/getCrop/{id}", CropDetails.class,map);
	}


	@Override
	public void updateCrop(int id, CropDetails crop) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		CropDetails cropDetails = new CropDetails();
		cropDetails.setId(crop.getId());
		cropDetails.setFarmerName(crop.getFarmerName());
		cropDetails.setCropType(crop.getCropType());
		cropDetails.setLocation(crop.getLocation());
		cropDetails.setPhoneNo(crop.getPhoneNo());
		cropDetails.setQuantity(crop.getQuantity());
		restTemplate.put("http://farmer/updatecropdetails/{id}", cropDetails,map);
	}


	@Override
	public void deleteCrop(int id) {
		Map<String, Integer> map  = new HashMap<>();
		map.put("id", id);
		restTemplate.delete("http://farmer/deleteCrop/{id}",map);
		
	}
	
	
	
	

}
