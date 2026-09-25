package com.example.demo.dto;

import lombok.Data;

@Data
public class ShippingRequestDTO {

	private Long orderId;
	private String shippingMethod;
	private String carrier;
}
