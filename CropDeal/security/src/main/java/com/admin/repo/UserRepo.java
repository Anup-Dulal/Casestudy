package com.admin.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.admin.model.User;

@Repository
public interface UserRepo extends MongoRepository<User, Integer> {

	User findByUserName(String username);
	
}
