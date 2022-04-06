package com.farmer.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.farmer.service.AccountNotFound;
import com.farmer.service.CropNotFound;
import com.farmer.service.InvoiceNotFound;
import com.farmer.service.USERNOTFOUND;
@SuppressWarnings({ "rawtypes", "unchecked" })
@ControllerAdvice
public class DealerExceptionClass {
	
	
	
	@ExceptionHandler(value = USERNOTFOUND.class)
	public ResponseEntity userNotFoundException(USERNOTFOUND exception) {
		return new ResponseEntity ("Dealer not found",HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = AccountNotFound.class)
	public ResponseEntity accountNotFound(AccountNotFound exception) {
		return new ResponseEntity ("Account Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvoiceNotFound.class)
	public ResponseEntity invoiceNotFound(InvoiceNotFound exception) {
		return new ResponseEntity ("Invoice Not Found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CropNotFound.class)
	public ResponseEntity cropDetailsNotFound(CropNotFound crop) {
		return new ResponseEntity ("Crop Not Found",HttpStatus.NOT_FOUND);
	}

}
