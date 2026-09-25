package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String check() {
		return "app running";
	}
	
	@PostMapping
	public UserDTO save(@RequestBody UserDTO userDTO) {
		return this.userService.save(userDTO);
	}
	
	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable Long userId) {
		return this.userService.findUserById(userId);
	}
 
	@GetMapping("/username/{username}")
	public UserDTO getUserByUserName(@PathVariable String username) {
		return this.userService.getUserByUserName(username);
	}
}
