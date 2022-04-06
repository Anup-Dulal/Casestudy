package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.FarmerInfo;
import com.admin.repo.AdminRepo;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class AdminService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AdminRepo adminRepo;

	public List<Dealer> getDealerDetails() {

		return restTemplate.getForObject("http://dealer/dealer/get", List.class);

		
	}

	public List<FarmerInfo> getFarmerInfoDetails() {
		return restTemplate.getForObject("http://farmer/farmer/getUser", List.class);
	}

	public void updateFarmerInfo(int id, FarmerInfo farmerInfo2) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		FarmerInfo farmerInfo = new FarmerInfo();
		farmerInfo.setId(farmerInfo2.getId());
		farmerInfo.setName(farmerInfo2.getName());
		farmerInfo.setAddress(farmerInfo2.getAddress());
		farmerInfo.setPhone(farmerInfo2.getPhone());
		restTemplate.put("http://farmer/farmer/updateuser/{id}", farmerInfo, params);

	}

	public void updateDealerInfo(int id, Dealer dealer) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		Dealer dealer2 = new Dealer();
		dealer2.setId(dealer.getId());
		dealer2.setUserName(dealer.getUserName());
		dealer2.setAddress(dealer.getAddress());
		dealer2.setPhoneNo(dealer.getPhoneNo());
		restTemplate.put("http://dealer/dealer/update/{id}", dealer2, params);

	}

	public FarmerInfo getFarmerById(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return restTemplate.getForObject("http://farmer/farmer/getUser/{id}", FarmerInfo.class, params);
	}

	public Dealer getDealerById(int id) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", id);
		return restTemplate.getForObject("http://dealer/dealer/get/{id}", Dealer.class, param);
	}

	public void deleteFarmerById(int id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		restTemplate.delete("http://farmer/farmer/delete/{id}", map);

	}

	public void deleteDealerById(int id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", id);
		restTemplate.delete("http://dealer/dealer/delete/{id}", map);

	}

	public Dealer addDealer(Dealer dealer) {
		Dealer dealer2 = new Dealer();
		dealer2.setId(dealer.getId());
		dealer2.setUserName(dealer.getUserName());
		dealer2.setAddress(dealer.getAddress());
		dealer2.setPhoneNo(dealer.getPhoneNo());
		return restTemplate.postForObject("http://dealer/dealer/add", dealer2, Dealer.class);
	}

	public void addAdmin(Admin admin) {
		adminRepo.save(admin);
		
	}

	public Admin getAdminDetails(String name) throws AdminNotFound {
		if(adminRepo.findByUserName(name) != null) {
			return adminRepo.findByUserName(name);
		}else {
			throw new AdminNotFound();
		}
		
	}

}
