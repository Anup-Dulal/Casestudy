package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.config.CustomerUserDetailsService;
import com.admin.helper.JwtUtil;
import com.admin.model.JwtResponse;
import com.admin.model.User;
import com.admin.model.UserModel;
import com.admin.repo.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepo repo;
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody UserModel model) throws Exception{
		try {
			
			authenticate(model.getUserName(), model.getPassword());
			
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails details =  this.customerUserDetailsService.loadUserByUsername(model.getUserName());
		String token =this.jwtUtil.generateToken(details);
		String user = repo.findByUserName(model.getUserName()).getRoles();
		String user1 = repo.findByUserName(model.getUserName()).getUserName();
		return ResponseEntity.ok(new JwtResponse(token,user,user1));
		

	}
	
	private void authenticate(String username,String password) throws Exception {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("User Disabled");
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("Invalid Credentials");
		}
	}


}
