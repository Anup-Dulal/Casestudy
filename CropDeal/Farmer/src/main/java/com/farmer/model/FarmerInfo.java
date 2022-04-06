package com.farmer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter	
@Document
public class FarmerInfo {
	
	@Id
	private int id;
	private String name;
	private String email;
	private Long phone;
	private String address;
}
