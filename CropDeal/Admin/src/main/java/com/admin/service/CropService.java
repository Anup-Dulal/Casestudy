package com.admin.service;

import java.util.List;

import com.admin.model.CropDetails;

public interface CropService {

	CropDetails addCropDetails(CropDetails cropDetails);

	List<CropDetails> getCrop();

	CropDetails getCropById(int id);

	void updateCrop(int id, CropDetails crop);

	void deleteCrop(int id);

}
