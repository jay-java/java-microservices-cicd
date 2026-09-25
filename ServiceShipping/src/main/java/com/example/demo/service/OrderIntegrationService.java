package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.feignclients.OrderFeignClient;

@Service
public class OrderIntegrationService {

	@Autowired
	private OrderFeignClient orderFeignClient;

	public OrderResponseDTO fetchOrderDetails(Long orderId) {
		return orderFeignClient.fetchOrderDetails(orderId);
	}
}
