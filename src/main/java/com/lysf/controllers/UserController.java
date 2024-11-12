package com.lysf.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lysf.dtos.UserDto;
import com.lysf.models.User;
import com.lysf.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	

	@GetMapping("/{id}")
	@Operation(description = "Busca por ID")
	public ResponseEntity<User> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	@GetMapping("/all")
	@Operation(description = "Busca todos")
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	@PostMapping
	@Operation(description = "Cria um usuario")
	public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	@PutMapping("/{id}")
	@Operation(description = "atualiza usuario")
	ResponseEntity<User> updateUser(@PathVariable(name = "id")UUID id,
			@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userDto));
	}
	@DeleteMapping("/{id}")
	@Operation(description = "deleta usuario")
	ResponseEntity<User> deleteUser(@PathVariable(name = "id")UUID id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
