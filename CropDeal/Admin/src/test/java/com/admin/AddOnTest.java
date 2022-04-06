package com.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.admin.model.AddOnList;
import com.admin.repo.AddOnRepo;
import com.admin.service.AddOnNotFound;
import com.admin.service.AddOnService;

@SpringBootTest
class AddOnTest {

	@Autowired
	private AddOnService addOnService;
	
	@MockBean
	private AddOnRepo addOnRepo;
	
	@Test
	void addAddOnTest() {
		AddOnList addOnList = new AddOnList(1L, "Delete Inactive", "ACTIVE");
		when(addOnRepo.save(addOnList)).thenReturn(addOnList);
		assertEquals(addOnList, addOnService.addAddOnList(addOnList));
	}
	@Test
	void getAddOnTest() {
		when(addOnRepo.findAll()).thenReturn(Stream.of(new AddOnList(1L, "Delete Inactive", "ACTIVE")).collect(Collectors.toList()));
		assertEquals(1, addOnService.getAddOnList().size());
	}
	@Test
	void getAddOnById() throws AddOnNotFound {
		Optional<AddOnList> addOnList = Optional.of(new AddOnList(1L, "Delete Inactive", "ACTIVE"));
		when(addOnRepo.findById(1L)).thenReturn(addOnList);
		Optional<AddOnList> list = addOnService.getAddOnListById(1L);
		assertEquals("ACTIVE", list.get().getStatus());
	}
	@Test
	void deleteaddOnTest() {
		AddOnList addOnList = new AddOnList(1L, "Delete Inactive", "ACTIVE");
		addOnRepo.delete(addOnList);
		verify(addOnRepo,times(1)).delete(addOnList);
	}
	

}
