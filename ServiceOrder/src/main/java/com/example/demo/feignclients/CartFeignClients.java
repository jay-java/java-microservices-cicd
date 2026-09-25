package com.example.demo.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CartItemResponseDTO;

@FeignClient(name="SERVICECART",path="/cart")
public interface CartFeignClients {

	@GetMapping("/{userId}")
	public List<CartItemResponseDTO> getCartItemByUser(@PathVariable Long userId);
}
