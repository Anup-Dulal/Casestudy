package com.farmer.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmer.model.Dealer;

public interface DealerRepo extends MongoRepository<Dealer, Integer> {

	Optional<Dealer> findById(int id);

	void deleteById(int id);

	Dealer findByUserName(String name);

}
