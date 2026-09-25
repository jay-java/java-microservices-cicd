package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorAPI {

	private String message; //client error
	private String status; //400
	private String error; //product not found
	private LocalDateTime localDateTime = LocalDateTime.now();
}
