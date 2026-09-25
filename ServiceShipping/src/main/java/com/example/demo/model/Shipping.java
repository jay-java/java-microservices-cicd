package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private String shippingMethod; //standard, next day, same day
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	private String status; //shipped, in transit, delivered
	private String carrier; //fedex, ups, shadow, DHL

}
