package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO request) {
		return orderService.placeOrder(request);
	}
	
	@GetMapping("/{orderId}")
	public OrderResponseDTO fetchOrderDetails(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}
}
