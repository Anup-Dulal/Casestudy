package com.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddOnList {
	
	@Id
	private Long id;
	private String todo;
	private String status;


	public AddOnList(Long id, String todo, String status) {
		super();
		this.id = id;
		this.todo = todo;
		this.status = status;
	}
	
	public AddOnList() {

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
