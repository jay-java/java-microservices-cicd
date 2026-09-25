package com.example.demo.dto;

import lombok.Data;

@Data
public class CartItemRequestDTO {

	private Long userId;

	private Long productId;

	private Integer qty;
}
