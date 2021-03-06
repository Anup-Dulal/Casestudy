package com.admin.model;

public class UserModel {
	
	public static final String SEQUENCE_NAME = "user_sequence";
	
	
	private int id;
	private String userName;
	private String password;
	private String email;
	private String roles;
	private String address;
	private String phoneNo;
	
	
	public UserModel(Integer id,String userName, String password, String email, String roles, String address, String phoneNo) {
		super();
		this.id=id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	public UserModel() {

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
	
	
	