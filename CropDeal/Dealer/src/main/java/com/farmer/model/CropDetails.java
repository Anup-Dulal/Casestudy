package com.farmer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CropDetails {
	@Id
	private int id;
	private String cropType;
	private Long quantity;
	private String location;
	private String farmerName;
	private Long phoneNo;
	
	public CropDetails(int id, String cropType, Long quantity, String location, String farmerName, Long phoneNo) {
		super();
		this.id = id;
		this.cropType=cropType;
		this.quantity = quantity;
		this.location = location;
		this.farmerName = farmerName;
		this.phoneNo = phoneNo;
	}
	public CropDetails() {
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
