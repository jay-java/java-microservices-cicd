package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShippingResponseDTO {

	private Long orderId;
	private String shippingMethod;
	private String status;
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	private String carrier;
	private OrderResponseDTO orderResponseDTO;
}
