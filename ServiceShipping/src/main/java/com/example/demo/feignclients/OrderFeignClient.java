package com.example.demo.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.OrderResponseDTO;

@FeignClient(name = "SERVICEORDER", path = "/orders")
public interface OrderFeignClient {

	
	@GetMapping("/{orderId}")
	public OrderResponseDTO fetchOrderDetails(@PathVariable Long orderId);

}
