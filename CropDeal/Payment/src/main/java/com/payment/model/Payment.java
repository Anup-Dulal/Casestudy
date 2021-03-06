package com.payment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Document
public class Payment {
	
	@Id
	private Long id;
	private Long cardNo;
	private String date;
	private Long cV;
	private String dealerName;
	private String farmerName;
	private Long amount;
	private Long farmerAccountNo;
	private int pinNo;
	
	public Payment() {
 }
	
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCardNo() {
		return cardNo;
	}
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getCV() {
		return cV;
	}
	public void setCV(Long cV) {
		this.cV = cV;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getFarmerAccountNo() {
		return farmerAccountNo;
	}
	public void setFarmerAccountNo(Long farmerAccountNo) {
		this.farmerAccountNo = farmerAccountNo;
	}
	public int getPinNo() {
		return pinNo;
	}
	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}
	
	
}
