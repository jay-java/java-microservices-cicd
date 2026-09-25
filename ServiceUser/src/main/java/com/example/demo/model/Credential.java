package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_redentials")
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long credentialId;

	@Column(unique = true)
	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

}
