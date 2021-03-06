package com.farmer.controller;

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

import com.farmer.model.FarmerInfo;
import com.farmer.model.Invoice;
import com.farmer.model.Payment;
import com.farmer.service.FARMERINFONOTFOUND;
import com.farmer.service.FarmerService;
import com.farmer.service.InvoiceService;
import com.farmer.service.PaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeResource {

	@Autowired
	FarmerService farmerService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private PaymentService paymentService;


	@GetMapping("/farmer/getUser")
	public List<FarmerInfo> getFarmerinfo() {
		return farmerService.getFarmer();
	}
	
	@PostMapping("/farmer/addUser")
	public FarmerInfo saveFarmerinfo(@RequestBody FarmerInfo info) {
		return farmerService.saveFarmerinfo(info);
	}

	@GetMapping("/farmer/getUser/{id}")
	public Optional<FarmerInfo> getFarmerInfoById(@PathVariable("id") int id) throws FARMERINFONOTFOUND {
		return farmerService.getFamerInfoById(id);
	}

	@PutMapping("/farmer/updateuser/{id}")
	public FarmerInfo updateFarmerInfo(@PathVariable("id") int id, @RequestBody FarmerInfo info) throws FARMERINFONOTFOUND {
		return farmerService.updateFarmerInfo(info, id);
	}

	@DeleteMapping("/farmer/delete/{id}")
	public void deleteFarmerIndo(@PathVariable("id") int id) {
		farmerService.deleteFarmerInfo(id);
	}

	@GetMapping("/getuser/{userName}")
	public FarmerInfo getFarmerInfoByName(@PathVariable("userName") String name) {
		return farmerService.getFarmerByName(name);
	}
	
	@GetMapping("/getInvoice/{userName}")
	public Invoice getInvoiceDetailsByName(@PathVariable("userName") String name) {
		return invoiceService.getInvoiceDetailsByName(name);
	}
	
	@GetMapping("/getPayment/{userName}")
	public Payment getPaymentDetailsByName(@PathVariable("userName") String name) {
		return paymentService.getPaymentDetails(name);
	}

}
