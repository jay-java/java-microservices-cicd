package com.example.demo.service;

import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartItemRequestDTO;
import com.example.demo.dto.CartItemResponseDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl {

	@Autowired 
	private CartRepository repository;
	@Autowired
	private ProductRestIntegrationServices productRestIntegrationServices;
	@Autowired
	private ProductFeignIntegrationService productFeignIntegrationService;

	public CartItemResponseDTO addToCart(CartItemRequestDTO cartItemRequestDTO) {

		Long userId = cartItemRequestDTO.getUserId();
		Long productId = cartItemRequestDTO.getProductId();
		var response = this.productRestIntegrationServices.fetchProduct(productId);

		Cart cart = new Cart();
		BeanUtils.copyProperties(cartItemRequestDTO, cart);
		Cart dbCartItem = repository.save(cart);

		return mapToDto(dbCartItem, response);
	}

	private CartItemResponseDTO mapToDto(Cart dbCartItem, ProductResponseDTO response) {
		CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
		BeanUtils.copyProperties(dbCartItem, cartItemResponseDTO);
		cartItemResponseDTO.setProduct(response);
		return cartItemResponseDTO;
	}

	public List<CartItemResponseDTO> fetchCartItem(Long userId) {

		return repository.findByUserId(userId).stream()
				.map(cart -> {
			ProductResponseDTO product = productFeignIntegrationService.fetchProduct(cart.getProductId());
			return mapToDto(cart, product);
		}).collect(Collectors.toList());
	}

}
