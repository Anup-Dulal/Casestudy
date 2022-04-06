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
public class CropDetails {
	@Id
	private int id;
	private String cropType;
	private Long quantity;
	private String location;
	private String farmerName;
	private Long phoneNo;
	
	
	
}
