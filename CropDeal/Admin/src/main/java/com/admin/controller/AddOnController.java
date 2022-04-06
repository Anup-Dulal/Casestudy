package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.model.AddOnList;
import com.admin.service.AddOnNotFound;
import com.admin.service.AddOnService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AddOnController {
	
	@Autowired
	private AddOnService addOnService;
	
	@PostMapping("/addOnList")
	public AddOnList addAddOnList(@RequestBody AddOnList addOnList) {
		log.info("Inside Add Post Method");
		return addOnService.addAddOnList(addOnList);
	}
	
	@GetMapping("/getList")
	public List<AddOnList> getAddOnList(){
		log.info("Inside Get Method");
		return addOnService.getAddOnList();
	}
	
	@GetMapping("/getList/{id}")
	public Optional<AddOnList> getAddOnListById(@PathVariable("id") Long id) throws AddOnNotFound{
		return addOnService.getAddOnListById(id);
		
	}
	
	@PutMapping("/updateAddOn/{id}")
	public AddOnList updateAddOnList(@PathVariable("id") Long id,@RequestBody AddOnList addOnList) throws AddOnNotFound {
		return addOnService.updateAddOnList(id,addOnList);
	}
	
	@DeleteMapping("/deleteList/{id}")
	public void deleteList(@PathVariable("id") Long id) {
		addOnService.deleteList(id);
	}

}
