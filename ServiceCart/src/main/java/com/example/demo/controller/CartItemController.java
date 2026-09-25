package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartItemRequestDTO;
import com.example.demo.dto.CartItemResponseDTO;
import com.example.demo.service.CartServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartItemController {

	@Autowired
	private CartServiceImpl cartServiceImpl;

	@PostMapping
	public CartItemResponseDTO addToCart(@RequestBody CartItemRequestDTO request) {
		log.error("cart controller addtocart");
		return cartServiceImpl.addToCart(request);
	}
	
	@GetMapping("/{userId}")
	public List<CartItemResponseDTO> getCartItemByUser(@PathVariable Long userId) {
		return cartServiceImpl.fetchCartItem(userId);
	}
}
