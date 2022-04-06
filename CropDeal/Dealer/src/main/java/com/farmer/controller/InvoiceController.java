package com.farmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.model.Invoice;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.InvoiceService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dealer")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	
	
	@Autowired
	KafkaTemplate<String, Invoice> kafkaTemplate;
	
	private static final String TOPIC = "SecondTopic";
	
	@PostMapping("/addInvoice")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		kafkaTemplate.send(TOPIC,invoice);
		return invoiceService.addInvoice(invoice);
	}
	

	
	@GetMapping("/getInvoice/{userName}")
	public Invoice getInvoiceByName(@PathVariable("userName") String name) throws InvoiceNotFound {
		return invoiceService.getInvoiceByName(name);
	}

}
