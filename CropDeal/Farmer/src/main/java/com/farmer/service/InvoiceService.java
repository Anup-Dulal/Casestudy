package com.farmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.farmer.model.Invoice;
import com.farmer.repo.InvoiceRepo;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@KafkaListener(topics = "SecondTopic", groupId = "id", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void publishInvoice(Invoice invoice) {
		invoiceRepo.save(invoice);
	}

	public Invoice getInvoiceDetailsByName(String name) {
		return invoiceRepo.findByFarmerName(name);
	}

}
