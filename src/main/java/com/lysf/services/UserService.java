package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.UserDto;
import com.lysf.models.User;
import com.lysf.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User findById(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User createUser(UserDto userDto) {
		var user = new User();
		BeanUtils.copyProperties(userDto, user);
		return userRepository.save(user);
	}

	public User updateUser(UUID id, UserDto userDto) {

		var user = findById(id);
		if (userDto.name() != null) {
			user.setName(userDto.name());
		}
		if (userDto.email() != null) {
			user.setEmail(userDto.email());
		}
		if (userDto.password() != null) {
			user.setPassword(userDto.password());
		}
		if (userDto.address() != null) {
			user.setAddress(userDto.address());
		}

		return userRepository.save(user);
	}

	public void deleteUser(UUID id) {
		var user = findById(id);
		userRepository.delete(user);
	}
}
