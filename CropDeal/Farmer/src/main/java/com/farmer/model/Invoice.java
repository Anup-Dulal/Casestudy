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
public class Invoice {
	
	@Id
	private String id;
	private String dealerName;
	private String farmerName;
	private String cropName;
	private Long quantity;
	private Long price;
	private Long total;
	
	
}
