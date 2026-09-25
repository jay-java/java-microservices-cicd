package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

	ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

	private String role;
}
