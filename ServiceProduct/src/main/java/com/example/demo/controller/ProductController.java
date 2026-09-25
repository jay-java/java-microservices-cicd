package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String msg() {
		return "running";
	}

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		var result = this.productService.save(product);
		return new ResponseEntity<Product>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{productId}")
	public ProductResponseDTO getProductById(@PathVariable Long productId) {
		return this.productService.getProductById(productId);
	}

	@GetMapping("/allproducts")
	public List<Product> getAllProduct() {
		return this.productService.getAllProducts();
	}

//	@PutMapping("/{productId}")
//	public Product updateProduct(@RequestBody Product product, @PathVariable Long productId) {
//		return this.productService.updateProduct(product, productId);
//	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long productId) {
		this.productService.deleteProduct(productId);
	}
}
