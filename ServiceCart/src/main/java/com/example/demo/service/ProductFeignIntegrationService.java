package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.feignclients.ProductFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFeignIntegrationService {

	@Autowired
	private ProductFeignClient productFeignClient;

	public ProductResponseDTO fetchProduct(Long productId) {
		return productFeignClient.getProductById(productId);
	}

	
}
