package com.admin.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.admin.model.AddOnList;

public interface AddOnRepo extends MongoRepository<AddOnList, Long> {

}
