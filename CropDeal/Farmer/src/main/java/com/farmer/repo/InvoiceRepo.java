package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.Invoice;

public interface InvoiceRepo extends MongoRepository<Invoice, Long> {

	Invoice findByFarmerName(String name);

}
