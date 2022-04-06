package com.farmer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.farmer.model.CropDetails;

@Repository
public interface CropRepo extends MongoRepository<CropDetails, Integer>{

	CropDetails findByFarmerName(String name);

}
