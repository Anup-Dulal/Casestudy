package com.admin.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.Admin;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {

	Admin findByUserName(String userName);
	

}
