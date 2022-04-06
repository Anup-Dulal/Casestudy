package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.CropDetails;

public interface CropRepo extends MongoRepository<CropDetails,Integer> {

}
