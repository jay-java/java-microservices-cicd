package com.example.demo.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProductResponseDTO;

//it will fetch url from eureka any of available services
@FeignClient(name = "SERVICEPRODUCT",path="/products/v1")
@LoadBalancerClient
public interface ProductFeignClient {

	@GetMapping("/{productId}")
	public ProductResponseDTO getProductById(@PathVariable Long productId);
}
