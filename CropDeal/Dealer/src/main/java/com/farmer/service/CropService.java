package com.farmer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.farmer.model.CropDetails;
import com.farmer.repo.CropRepo;

@Service
public class CropService {
	
	@Autowired
	private CropRepo cropRepo;
	
	@KafkaListener(topics = "NewTopic",groupId = "group_id", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void publish (CropDetails cropDetails) {
		cropRepo.save(cropDetails);
	}

	public List<CropDetails> getCropDetails() {
		return cropRepo.findAll();
	}

	public Optional<CropDetails> getCropDetailsById(int id) throws CropNotFound {
		if(cropRepo.findById(id).isEmpty()) {
			throw new CropNotFound();
		}else {
			return cropRepo.findById(id);
		}
	}

}
