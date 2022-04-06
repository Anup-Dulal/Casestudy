package com.farmer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.model.Dealer;
import com.farmer.repo.DealerRepo;

@Service
public class DealerServiceImpl implements DealerService {
	
	@Autowired
	private DealerRepo dealerRepo;

	@Override
	public Dealer addDealerInfo(Dealer dealer) {
		return dealerRepo.save(dealer);
		
	}

	@Override
	public List<Dealer> getDealerInfo() {
		return dealerRepo.findAll();
	}

	@Override
	public Dealer updateDealerInfo(Dealer dealer, int id) throws USERNOTFOUND {
		Optional<Dealer> dealer1 = dealerRepo.findById(id);
		if(dealer1.isPresent()) {
			Dealer deal = dealer1.get();
			deal.setUserName(dealer.getUserName());
			deal.setAddress(dealer.getAddress());
			deal.setPhoneNo(dealer.getPhoneNo());
			return dealerRepo.save(deal);
		}else {
			throw new USERNOTFOUND();
		}
	}

	@Override
	public void deleteDealer(int id) {
		dealerRepo.deleteById(id);
		
		
	}

	@Override
	public Optional<Dealer> getDealerById(int id) throws USERNOTFOUND {
		if(dealerRepo.findById(id).isEmpty()) {
			throw new USERNOTFOUND();
		}else {
			return dealerRepo.findById(id);
		}
	}

	@Override
	public Dealer getDealerByName(String name) {
		return dealerRepo.findByUserName(name);
		
	}

	

}
