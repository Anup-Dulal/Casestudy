package com.farmer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.FarmerInfo;
import com.farmer.repo.FarmerRepo;

@Service
public class FarmerService {
	
	@Autowired
	private FarmerRepo farmerRepo;
	
	public FarmerInfo saveFarmerinfo(FarmerInfo info) {
		return farmerRepo.save(info);
	}

	public List<FarmerInfo> getFarmer() {
		return farmerRepo.findAll();
	}

	public FarmerInfo updateFarmerInfo(FarmerInfo info, int id) throws FARMERINFONOTFOUND {
		Optional<FarmerInfo> info1 = farmerRepo.findById(id);
		if(info1.isPresent()) {
			FarmerInfo inf = info1.get();
			inf.setName(info.getName());
			inf.setPhone(info.getPhone());
			inf.setAddress(info.getAddress());
			inf.setEmail(info.getEmail());
			return farmerRepo.save(inf);
			
		}else {
			throw new FARMERINFONOTFOUND();
		}
	}

	public Optional<FarmerInfo> getFamerInfoById(int id) throws FARMERINFONOTFOUND {
		if (farmerRepo.findById(id).isEmpty()) {
			throw new FARMERINFONOTFOUND();
		}else {
			return farmerRepo.findById(id);
		}
		
	}

	public void deleteFarmerInfo(int id) {
		farmerRepo.deleteById(id);
		
	}

	public FarmerInfo getFarmerByName(String name)  {
			return farmerRepo.findByName(name);
	
		
	}
	
	
	

}
