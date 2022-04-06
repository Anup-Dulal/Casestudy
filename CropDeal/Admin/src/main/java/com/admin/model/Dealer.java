package com.admin.model;

import org.springframework.data.annotation.Id;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Dealer {
	
	@Id
	private	int id;
	private String userName;
	private String address;
	private Long phoneNo;
	public Dealer(int id, String userName, String address, Long phoneNo) {
		super();
		this.id = id;
		this.userName = userName;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	public Dealer() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	

}
