package com.example.demo.service;
//If you want to communicate between two serveices we have concept called restTemplate

import javax.management.RuntimeErrorException;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductRestIntegrationServices {

	@Autowired
	private RestTemplate restTemplate;

	private static final String PRODUCT_SERVICE_URL = "http://localhost:3309/products/v1/{productId}";

	public ProductResponseDTO fetchProduct(Long productId) {

		try {
			var responseEntity = restTemplate.getForEntity(PRODUCT_SERVICE_URL, ProductResponseDTO.class, productId);
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				return responseEntity.getBody();
			} else {
				throw new ResourceNotFoundException("failed to fetch product information");
			}
		} catch (Exception e) {
			log.error("Error occurred while fetching product", e);
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
