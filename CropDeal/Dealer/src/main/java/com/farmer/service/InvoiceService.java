package com.farmer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.Invoice;
import com.farmer.repo.InvoiceRepo;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepo invoiceRepo;

	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}

	public Invoice getInvoiceByName(String name) throws InvoiceNotFound {
		if(invoiceRepo.findByDealerName(name) != null) {
			return invoiceRepo.findByDealerName(name);
		}else {
			throw new InvoiceNotFound();
		}
		
	}
	
	

}
