package com.farmer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Invoice {
	@Id
	private String id;
	private String dealerName;
	private String farmerName;
	private String cropName;
	private Long quantity;
	private Long price;
	private Long total;
	public Invoice(String id, String dealerName, String farmerName, String cropName, Long quantity, Long price,
			Long total) {
		super();
		this.id = id;
		this.dealerName = dealerName;
		this.farmerName = farmerName;
		this.cropName = cropName;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}
	public Invoice() {

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
