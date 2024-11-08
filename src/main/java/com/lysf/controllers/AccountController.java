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

import com.lysf.dtos.AccountDto;
import com.lysf.models.Account;
import com.lysf.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(id));
	}
	@GetMapping("/all")
	public ResponseEntity<List<Account>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
	}
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDto));
	}
	@PutMapping("/{id}")
	ResponseEntity<Account> updateAccount(@PathVariable(name = "id")UUID id,
			@RequestBody AccountDto accountDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(id, accountDto));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<Account> deleteAccount(@PathVariable(name = "id")UUID id){
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}
	
}
