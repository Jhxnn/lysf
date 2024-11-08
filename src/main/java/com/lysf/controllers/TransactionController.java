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

import com.lysf.dtos.CardDto;
import com.lysf.dtos.PixDto;
import com.lysf.dtos.TransactionDto;
import com.lysf.models.Transaction;
import com.lysf.models.enums.PaymentType;
import com.lysf.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	
	@Autowired
	TransactionService transactionService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.findById(id));
	}
	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAll());
	}
	@PostMapping("/{accountId}/{payType}")
	public ResponseEntity<Transaction> createTransaction(@PathVariable(name = "id")UUID id, 
			@PathVariable(name = "type") PaymentType payType,
			@RequestBody CardDto cardDto,
			@RequestBody TransactionDto transactionDto,
			@RequestBody PixDto pixDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(id, payType, transactionDto, cardDto, pixDto));
	}
	
	@PutMapping("/refund/{id}")
	ResponseEntity<Transaction> refund(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.refund(id));
	}
	@PutMapping("/{id}")
	ResponseEntity<Transaction> updateTransaction(@PathVariable(name = "id")UUID id,
			@RequestBody TransactionDto transactionDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.updateTransaction(id, transactionDto));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<Transaction> deleteTransaction(@PathVariable(name = "id")UUID id){
		transactionService.deleteTransaction(id);
		return ResponseEntity.noContent().build();
	}
}
