package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcetionHandler {

	// annotation for specific exception
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleException(ProductNotFoundException ex) {
		ErrorAPI e = new ErrorAPI();
		e.setError(ex.getMessage());
		e.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		e.setMessage("client side error");
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ErrorAPI> handleException(ResourceAlreadyExistException ex) {
		ErrorAPI e = new ErrorAPI();
		e.setError(ex.getMessage());
		e.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
		e.setMessage("data conflict");
		return new ResponseEntity<>(e, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorAPI> handleException(HttpMessageNotReadableException ex) {
		ErrorAPI e = new ErrorAPI();
		e.setError(ex.getMessage());
		e.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		e.setMessage("Malformed json data");
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

	// for general exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex) {
		ErrorAPI e = new ErrorAPI();
		e.setError(ex.getMessage());
		e.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		e.setMessage("Something went wrong");
		return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
