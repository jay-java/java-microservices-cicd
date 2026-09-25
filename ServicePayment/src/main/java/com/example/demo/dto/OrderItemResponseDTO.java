package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemResponseDTO {

	private Long productId;
	private Integer quantity;
	private BigDecimal price;
}
