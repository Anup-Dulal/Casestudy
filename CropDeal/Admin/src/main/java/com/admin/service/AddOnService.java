package com.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.model.AddOnList;
import com.admin.repo.AddOnRepo;

@Service
public class AddOnService {
	
	@Autowired
	private AddOnRepo addOnRepo;
	

	public AddOnList addAddOnList(AddOnList addOnList) {
		return addOnRepo.save(addOnList);
	}


	public List<AddOnList> getAddOnList() {
		return addOnRepo.findAll();
	}


	public Optional<AddOnList> getAddOnListById(Long id) throws AddOnNotFound {
		if(addOnRepo.findById(id).isEmpty()) {
			throw new AddOnNotFound();
		}else {
			return addOnRepo.findById(id);
		}
		
	}


	public AddOnList updateAddOnList(Long id, AddOnList addOnList) throws AddOnNotFound {
		Optional<AddOnList> addOnList2 = addOnRepo.findById(id);
		if(addOnList2.isPresent()) {
			AddOnList addOnList3 = addOnList2.get();
			addOnList3.setId(addOnList.getId());
			addOnList3.setTodo(addOnList.getTodo());
			addOnList3.setStatus(addOnList.getStatus());
			return addOnRepo.save(addOnList3);
		}else {
			throw new AddOnNotFound();
		}
	}


	public void deleteList(Long id) {
		addOnRepo.deleteById(id);
	}
	

}
