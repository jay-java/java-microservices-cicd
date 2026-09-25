package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CredentialDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO save(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);

		Credential credential = new Credential();
		BeanUtils.copyProperties(userDTO.getCredential(), credential);

		// set bidiractional relation ship
		credential.setUser(user);
		user.setCredential(credential);

		User dbUser = userRepository.save(user);

		return maptoDto(dbUser);
	}

	private UserDTO maptoDto(User dbUser) {
		// TODO Auto-generated method stub

		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(dbUser, userDto);

		CredentialDTO credentialDTO = new CredentialDTO();
		BeanUtils.copyProperties(dbUser.getCredential(), credentialDTO);

		userDto.setCredential(credentialDTO);
		return userDto;
	}

	public UserDTO findUserById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optional = this.userRepository.findById(userId);
		if (optional.isPresent()) {
			User dbUser = optional.get();
			return maptoDto(dbUser);
		} else {
			throw new ResourceNotFoundException("user not found");
		}
	}

	public UserDTO getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByCredentialUsername(username)
		 	.map(user -> maptoDto(user))
		 	.orElseThrow(() -> new ResourceNotFoundException("User not exist"));
	}
}
