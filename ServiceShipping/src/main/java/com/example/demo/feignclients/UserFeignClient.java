package com.example.demo.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.UserDTO;

@FeignClient(name = "SERVICEUSER", path = "/users")
public interface UserFeignClient {

	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable Long userId);
}
