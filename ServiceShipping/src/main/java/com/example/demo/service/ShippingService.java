package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.dto.ShippingRequestDTO;
import com.example.demo.dto.ShippingResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Shipping;
import com.example.demo.repository.ShippingRepository;

@Service
public class ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;

	@Autowired
	private OrderIntegrationService orderIntegrationService;

	public ShippingResponseDTO shipOrder(ShippingRequestDTO request) {
		OrderResponseDTO orderDetails = orderIntegrationService.fetchOrderDetails(request.getOrderId());

		if (orderDetails != null) {
			Shipping shipping = new Shipping();
			BeanUtils.copyProperties(request, shipping);
			shipping.setStatus("SHIPPED");
			shipping.setShippedAt(LocalDateTime.now());
			Shipping dbShipping = shippingRepository.save(shipping);
			return mapToDto(dbShipping, orderDetails);
		} else {
			throw new ResourceNotFoundException("Invalid order Id");
		}
	}

	private ShippingResponseDTO mapToDto(Shipping dbShipping, OrderResponseDTO orderDetails) {
		ShippingResponseDTO response = new ShippingResponseDTO();
		BeanUtils.copyProperties(dbShipping, response);
		response.setOrderResponseDTO(orderDetails);
		return response;
	}
}
