package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponseDTO {

	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	private LocalDateTime placedAt = LocalDateTime.now();
	private List<OrderItemResponseDTO> items;
}
