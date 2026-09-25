package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PaymentRequestDTO;
import com.example.demo.dto.PaymentResponseDTO;
import com.example.demo.service.PaymentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

	private PaymentService paymentService;

	@GetMapping
	public String appRunning() {
		return "running";
	}
	
	@PostMapping
	public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {
		log.info("make payment");
		return paymentService.processPayment(request);
	}

	@GetMapping("/{paymentId}")
	public PaymentResponseDTO getPayment(@PathVariable Long paymentId) {
		log.info("get payment");
		return paymentService.getPaymentDetails(paymentId);
	}

}
