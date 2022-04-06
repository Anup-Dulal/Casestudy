package com.farmer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BankDetails {
	@Id
	private int id;
	private Long accountno;
	private String userName;
	private String bank;
	
	public BankDetails(Integer id,Long accountno, String userName, String bank) {
		super();
		this.id=id;
		this.accountno = accountno;
		this.userName = userName;
		this.bank = bank;
	}
	public BankDetails() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getAccountno() {
		return accountno;
	}
	public void setAccountno(Long accountno) {
		this.accountno = accountno;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	

}
