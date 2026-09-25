package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.feignclients.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j
public class UserFeignIntegrationService {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public UserDTO fetchUser(Long userId) {
		return userFeignClient.getUserById(userId);
	}
}
