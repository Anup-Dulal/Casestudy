package com.admin.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.admin.model.CropDetails;

public interface CropRepo extends MongoRepository<CropDetails, Integer> {


}
