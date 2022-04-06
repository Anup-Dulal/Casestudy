package com.farmer.service;

import java.util.List;
import java.util.Optional;

import com.farmer.model.Dealer;

public interface DealerService {


	Dealer addDealerInfo(Dealer dealer);

	List<Dealer> getDealerInfo();

	Dealer updateDealerInfo(Dealer dealer, int id) throws USERNOTFOUND;

	void deleteDealer(int id);

	Optional<Dealer> getDealerById(int id);

	Dealer getDealerByName(String name);
	
	

}
