package com.example.demo.dto;

import com.example.demo.model.Role;

import lombok.Data;

@Data
public class UserDTO {

	private Long userId;
	private String name;
	private String email;
	private String phone;
	private CredentialDTO credential;
}
