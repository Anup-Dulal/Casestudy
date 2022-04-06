package com.farmer;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.controller.HomeController;
import com.farmer.model.Dealer;
import com.farmer.repo.DealerRepo;
import com.farmer.service.DealerService;
import com.farmer.service.USERNOTFOUND;


@SpringBootTest
class DealerApplicationTests {
	
	@Autowired
	private DealerService dealerService;
	
	
	
	@MockBean
	private DealerRepo dealerRepo;
	
	@Autowired
	HomeController controller;
	
	public void contextLoads() {
		Assertions.assertThat(controller).isNot(null);
	}
	
	@Test
	void main() {
		DealerApplication.main(new String[] {});
	}
	
	

	
	@Test
	void getDealerTest() {
		when(dealerRepo.findAll()).thenReturn(Stream
				.of(new Dealer(1,"john","Delhi",99999999L), new Dealer(2,"smith","Bihar",111222334L)).collect(Collectors.toList()));
		assertEquals(2, dealerService.getDealerInfo().size());
	}
	
	@Test
	void saveDealerTest() {
		Dealer dealer = new Dealer();
		dealer.setId(1);
		dealer.setUserName("smith");
		dealer.setAddress("Delhi");
		dealer.setPhoneNo(11223L);
		when(dealerRepo.save(dealer)).thenReturn(dealer);
		assertEquals(dealer, dealerService.addDealerInfo(dealer));
	}
	
	@Test
	void deleteDealerTest() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		dealerService.deleteDealer(3);
		verify(dealerRepo,times(1)).deleteById(3);
	}
	
	@Test
	void updateDealerTest() throws USERNOTFOUND {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		assertThrows(USERNOTFOUND.class, ()->{
			when(dealerRepo.findByUserName("hari")).thenReturn(dealer);
			Dealer dealer1 = new Dealer();
			dealer1.setId(dealer.getId());
			dealer1.setUserName(dealer.getUserName());
			dealer1.setAddress(dealer.getAddress());
			dealer1.setPhoneNo(dealer.getPhoneNo());
			assertEquals(dealer, dealerService.updateDealerInfo(dealer1, 3));
		});
		Optional<Dealer> dealer1 = Optional.of(new Dealer(3,"hari","Bihar",55555555L));
		when(dealerRepo.findById(3)).thenReturn(dealer1);
		if(dealer1.isPresent()) {
		Dealer dealer2 = dealer1.get();
		dealer2.setAddress(dealer.getAddress());
		dealer2.setId(dealer.getId());
		when(dealerRepo.save(dealer2)).thenReturn(dealer2);
		assertEquals(dealer2.getAddress(),dealerService.updateDealerInfo(dealer2, 3).getAddress());
		}
	}
	@Test
	void getDealerByName() {
		Dealer dealer = new Dealer(3,"hari","Bihar",55555555L);
		when(dealerRepo.findByUserName("hari")).thenReturn(dealer);
		Dealer dealer2 = dealerService.getDealerByName("hari");
		assertEquals("Bihar", dealer2.getAddress());
	}
	
	@Test
	void getDealerById() {
		Optional<Dealer> dealer = Optional.of(new Dealer(3,"hari","Bihar",55555555L));
		when(dealerRepo.findById(3)).thenReturn(dealer);
		Optional<Dealer> dealer2 = dealerService.getDealerById(3);
		assertEquals("hari", dealer2.get().getUserName());
	}
	
	
	
	
	
}
