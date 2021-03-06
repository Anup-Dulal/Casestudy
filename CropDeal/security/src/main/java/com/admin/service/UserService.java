package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.FarmerInfo;
import com.admin.model.User;
import com.admin.model.UserModel;
import com.admin.repo.UserRepo;



@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	

	public void registerUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		user.setUserName(userModel.getUserName());
		user.setRoles(userModel.getRoles());
		user.setEmail(userModel.getEmail());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setAddress(userModel.getAddress());
		user.setPhoneNo(userModel.getPhoneNo());
		userRepo.save(user);
		String str = userModel.getRoles();
		String str2 = "FARMER";
		String str3 = "DEALER";
		if(str.equals(str2)) {
			FarmerInfo farmerInfo = new FarmerInfo();
			farmerInfo.setId(userModel.getId());
			farmerInfo.setName(userModel.getUserName());
			farmerInfo.setEmail(userModel.getEmail());
			farmerInfo.setAddress(userModel.getAddress());
			farmerInfo.setPhone(Long.parseLong(userModel.getPhoneNo()));
			restTemplate.postForObject("http://farmer/farmer/addUser", farmerInfo, FarmerInfo.class);	
		}else if(str.equals(str3)) {
			Dealer dealer = new Dealer();
			dealer.setId(userModel.getId());
			dealer.setUserName(userModel.getUserName());
			dealer.setAddress(userModel.getAddress());
			dealer.setPhoneNo(Long.parseLong(userModel.getPhoneNo()));
			restTemplate.postForObject("http://dealer/dealer/add", dealer, Dealer.class);
		}else {
			Admin admin = new Admin();
			admin.setId(String.valueOf(userModel.getId()));
			admin.setUserName(userModel.getUserName());
			admin.setEmail(userModel.getEmail());
			admin.setPassword(userModel.getPassword());
			admin.setRole(userModel.getRoles());
			restTemplate.postForObject("http://admin/admin/add", admin, Admin.class);
		}
		
	}


	public List<User> getUserDetails() {
		return userRepo.findAll();
	}


	public User getUserByName(String name) {
		return userRepo.findByUserName(name);
	}



}
