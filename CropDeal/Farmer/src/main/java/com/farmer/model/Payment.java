package com.farmer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {
	
	private Long id;
	private Long cardNo;
	private String date;
	private Long cV;
	private String dealerName;
	private String farmerName;
	private Long amount;
	private Long farmerAccountNo;
	private int pinNo;
}
