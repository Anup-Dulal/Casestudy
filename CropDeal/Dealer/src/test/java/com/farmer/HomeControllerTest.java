package com.farmer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmer.controller.HomeController;
import com.farmer.model.Dealer;
import com.farmer.service.DealerService;

@SpringBootTest
class HomeControllerTest {

	@InjectMocks
	HomeController controller;
	
	@Mock
	DealerService dealerService;
	
	@Test
	void addDealerTest() {
		when(dealerService.addDealerInfo(any(Dealer.class))).thenReturn( new Dealer(2,"smith","Bihar",111222334L));
		Dealer dealer = new Dealer(2,"smith","Bihar",111222334L);
		Dealer dealer2 = controller.addDealerinfo(dealer);
		assertThat(dealer2.getAddress()).isEqualTo("Bihar");
		assertThat(dealer2.getId()).isEqualTo(2);
		assertThat(dealer2.getPhoneNo()).isEqualTo(111222334L);
		assertThat(dealer2.getUserName()).isEqualTo("smith");
	}
	
	@Test
	void getDealerTest() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		Dealer dealer1 = new Dealer(4,"hari","Bihar",55555555L);
		List<Dealer> dealers = new ArrayList<Dealer>();
		dealers.add(dealer1);
		dealers.add(dealer);
		when(dealerService.getDealerInfo()).thenReturn(dealers);
		List<Dealer> dealers2 = controller.getDealerInfo();
		assertEquals(2, dealers2.size());
	}
	@Test
	void getDealerByIdTest() {
		Optional<Dealer> dealer = Optional.of(new Dealer(3,"hari","Bihar",55555555L));
		when(dealerService.getDealerById(3)).thenReturn(dealer);
		Optional<Dealer> dealer2 = controller.getDealerById(3);
		assertThat(dealer2.get().getAddress()).isEqualTo("Bihar");
	}
	
	@Test
	void getDealerByNameTest() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		when(dealerService.getDealerByName("hari")).thenReturn(dealer);
		Dealer dealer2 = controller.getDealerByName("hari");
		assertThat(dealer2.getPhoneNo()).isEqualTo(55555555L);
	}
	@Test
	void updateDealerTest() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		dealer.setAddress("USA");
		when(dealerService.updateDealerInfo(dealer, 3)).thenReturn(dealer);
		Dealer dealer2 = controller.updateDealerinfo(dealer, 3);
		assertThat(dealer2.getAddress()).isEqualTo("USA");
	}
	@Test
	void deleteDealerTest() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		controller.deleteDealer(3);
		verify(dealerService,times(1)).deleteDealer(3);
	}
	

}
