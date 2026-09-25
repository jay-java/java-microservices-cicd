package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentResponseDTO {

	private Long paymentId;
	private Long userId;
	private Long orderId;
	private String status;
	private BigDecimal amount;
	private OrderResponseDTO orderResponseDTO;
	
}
