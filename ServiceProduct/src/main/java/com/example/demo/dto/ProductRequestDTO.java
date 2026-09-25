package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequestDTO {

	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
}
