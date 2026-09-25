package com.example.demo.dto;

import com.example.demo.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CredentialDTO {

	private String username;
	private String password;
	private Role role;

}
