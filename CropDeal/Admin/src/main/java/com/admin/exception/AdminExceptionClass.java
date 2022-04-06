package com.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.admin.service.AddOnNotFound;
import com.admin.service.AdminNotFound;

@ControllerAdvice
public class AdminExceptionClass {
	
	@ExceptionHandler(value = AddOnNotFound.class)
	public ResponseEntity<?> addOnNotFound(AddOnNotFound exception){
		return new ResponseEntity<>("Add On List Not Found",HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(value = AdminNotFound.class )
	public ResponseEntity<?> adminNotFound(AdminNotFound admin){
		return new ResponseEntity<>("Admin Info Not Found",HttpStatus.NOT_FOUND);
	}

}
