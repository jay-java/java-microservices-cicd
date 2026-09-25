package com.example.demo.service;

import com.example.demo.controller.OrderController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartItemResponseDTO;
import com.example.demo.dto.OrderItemResponseDTO;
import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.feignclients.CartFeignClients;
import com.example.demo.feignclients.ProductFeignClient;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartFeignClients cartFeignClients;

	@Autowired
	private ProductFeignClient productFeignClient;

	public OrderResponseDTO placeOrder(OrderRequestDTO request) {
		List<CartItemResponseDTO> cartItems = cartFeignClients.getCartItemByUser(request.getUserId());
		if (cartItems.isEmpty()) {
			throw new ResourceNotFoundException("cart items are empty");
		}
		BigDecimal totalPrice = calculatePrice(cartItems);
		List<OrderItem> orderItems = buildOrderItems(cartItems);
		System.out.println("ORDER ITEMS : " + orderItems);
		Order order = createOrder(request, totalPrice, orderItems);

		Order dbOrder = orderRepository.save(order);
		return mapToDto(dbOrder);
	}

	private OrderResponseDTO mapToDto(Order dbOrder) {
		OrderResponseDTO response = new OrderResponseDTO();
		BeanUtils.copyProperties(dbOrder, response, "items");
		response.setOrderId(dbOrder.getId());
		var orderItemResponse = dbOrder.getItems().stream().map(item -> {
			OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
			BeanUtils.copyProperties(item, orderItemResponseDTO);
			ProductResponseDTO product = productFeignClient.getProductById(item.getProductId());
			orderItemResponseDTO.setProduct(product);
			return orderItemResponseDTO;
		}).collect(Collectors.toList());
		response.setItems(orderItemResponse);
		return response;
	}

	private Order createOrder(OrderRequestDTO request, BigDecimal totalPrice, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setTotalPrice(totalPrice);
		order.setStatus("PLACED");

		for (OrderItem item : orderItems) {
			item.setOrder(order);
		}
		order.setItems(orderItems);
		return order;
	}

	private List<OrderItem> buildOrderItems(List<CartItemResponseDTO> cartItems) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (CartItemResponseDTO item : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getProduct().getPrice());
			orderItem.setProductId(item.getProductId());
			orderItem.setQuantity(item.getQty());
			orderItems.add(orderItem);
		}
		return orderItems;
	}

	private BigDecimal calculatePrice(List<CartItemResponseDTO> cartItems) {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItemResponseDTO cart : cartItems) {
			BigDecimal productPrice = cart.getProduct().getPrice();
			total = total.add(productPrice.multiply(BigDecimal.valueOf(cart.getQty())));
		}
		return total;
	}

	public OrderResponseDTO getOrderById(Long orderId) {
		return orderRepository.findById(orderId).map(this::mapToDto)
				.orElseThrow(() -> new RuntimeException("Order not found"));
	}

}
