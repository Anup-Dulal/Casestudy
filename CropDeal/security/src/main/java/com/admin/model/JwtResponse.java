package com.admin.model;

public class JwtResponse {
	
	private String token;
	private String roles;
	private String name;

	public JwtResponse(String token, String roles, String name) {
		super();
		this.token = token;
		this.roles=roles;
		this.name=name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
	
	

}
