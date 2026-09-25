package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product save(Product product) {
		return this.productRepository.save(product);
	}

	public ProductResponseDTO save(ProductRequestDTO product) {
		// convert request to entity
		Product p = new Product();
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setDescription(product.getDescription());
		p.setStock(product.getStock());

		var dbResponse = this.productRepository.save(p);

		ProductResponseDTO response = new ProductResponseDTO();
		BeanUtils.copyProperties(dbResponse, response);
		return response;
	}

	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	public ProductResponseDTO getProductById(Long productId) {
		Optional<Product> p = this.productRepository.findById(productId);
		if(p.isPresent()) {
			Product product= p.get();
			return mapToDto(product);
		}
		else {
			throw new ProductNotFoundException("product not found");
		}
	}

	private ProductResponseDTO mapToDto(Product product) {
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		BeanUtils.copyProperties(product, productResponseDTO);
		return productResponseDTO;
	}

	public void deleteProduct(Long productId) {
		this.productRepository.deleteById(productId);
	}

//	public Product updateProduct(Product product, Long productId) {
//		Product p = getProductById(productId);
//		p.setName(product.getName());
//		p.setDescription(product.getDescription());
//		p.setPrice(product.getPrice());
//		p.setStock(product.getStock());
//
//		return this.productRepository.save(p);
//	}
}
