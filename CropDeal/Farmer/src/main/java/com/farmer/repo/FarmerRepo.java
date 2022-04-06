package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.FarmerInfo;

public interface FarmerRepo extends MongoRepository<FarmerInfo, Integer> {

	FarmerInfo findByName(String name);

}
