package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.dto.PaymentRequestDTO;
import com.example.demo.dto.PaymentResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.feignclients.OrderFeignClient;
import com.example.demo.models.Payment;
import com.example.demo.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderFeignClient orderFeignClient;

	public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
		OrderResponseDTO orderDetails = orderFeignClient.fetchOrderDetails(request.getOrderId());
		if (orderDetails != null) {
			Payment payment = new Payment();
			BeanUtils.copyProperties(request, payment);
			payment.setStatus("SUCCESS");
			Payment dbPayment = paymentRepository.save(payment);
			return mapToDto(dbPayment, orderDetails);
		} else {
			throw new ResourceNotFoundException("order id not found");
		}
	}

	private PaymentResponseDTO mapToDto(Payment dbPayment, OrderResponseDTO orderDetails) {
		PaymentResponseDTO response = new PaymentResponseDTO();
		BeanUtils.copyProperties(dbPayment, response);
		response.setPaymentId(dbPayment.getId());
		response.setOrderResponseDTO(orderDetails);
		return response;
	}

	public PaymentResponseDTO getPaymentDetails(Long paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if (payment.isPresent()) {
			Payment dbPayment = payment.get();
			OrderResponseDTO orderDetails = orderFeignClient.fetchOrderDetails(dbPayment.getOrderId());
			return mapToDto(dbPayment, orderDetails);
		} else {
			throw new ResourceNotFoundException("payment id not found");
		}
	}
}
