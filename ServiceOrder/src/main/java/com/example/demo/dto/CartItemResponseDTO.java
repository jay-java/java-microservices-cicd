package com.example.demo.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {

	private Long userId;

	private Long productId;

	private Integer qty;

	private ProductResponseDTO product;

	private UserDTO user;

}
