package com.farmer.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.farmer.service.BANKACCOUNTNOTFOUND;
import com.farmer.service.CROPDETAILSNOTFOUND;
import com.farmer.service.FARMERINFONOTFOUND;
@SuppressWarnings({ "rawtypes", "unchecked" })
@ControllerAdvice
public class FarmerExceptionClass {
	
	
	
	@ExceptionHandler(value = BANKACCOUNTNOTFOUND.class)
	public ResponseEntity bankAccountNotFound(BANKACCOUNTNOTFOUND exception) {
		return new ResponseEntity ("Bank Account Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FARMERINFONOTFOUND.class)
	public ResponseEntity farmerInfoNotFound(FARMERINFONOTFOUND exception){
		return new ResponseEntity ("Farmer Info Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CROPDETAILSNOTFOUND.class)
	public ResponseEntity cropDetailsNotFound(CROPDETAILSNOTFOUND exception){
		return new ResponseEntity ("Crop Details Not Found",HttpStatus.NOT_FOUND);
	}

}
