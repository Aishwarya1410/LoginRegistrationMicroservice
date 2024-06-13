package com.springboot.auth_service.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<String> handleExpiredJwtException(HttpServletRequest request, Exception ex){
		String message = (String) request.getAttribute("expired");
		
		return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
