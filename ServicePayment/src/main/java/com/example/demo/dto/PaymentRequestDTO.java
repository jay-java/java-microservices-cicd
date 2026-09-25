package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDTO {

	private BigDecimal amount;
	private Long userId;
	private Long orderId;
}
