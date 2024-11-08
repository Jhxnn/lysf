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
import com.lysf.dtos.PaymentHistoryDto;
import com.lysf.models.PaymentHistory;
import com.lysf.services.PaymentHistoryService;

@RestController
@RequestMapping("/payment-history")
public class PaymentHistoryController {

	@Autowired
	PaymentHistoryService payHisService;
	

	@GetMapping("/{id}")
	public ResponseEntity<PaymentHistory> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(payHisService.findById(id));
	}
	@GetMapping("/all")
	public ResponseEntity<List<PaymentHistory>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(payHisService.findAll());
	}
	@PostMapping
	public ResponseEntity<PaymentHistory> createPaymentHistory(@RequestBody PaymentHistoryDto payHisDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payHisService.createPaymentHistory(payHisDto));
	}
	@PutMapping("/{id}")
	ResponseEntity<PaymentHistory> updatePaymentHistory(@PathVariable(name = "id")UUID id,
			@RequestBody PaymentHistoryDto payHisDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payHisService.updatePayHis(id, payHisDto));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<PaymentHistory> deletePaymentHistory(@PathVariable(name = "id")UUID id){
		payHisService.deletePayHis(id);
		return ResponseEntity.noContent().build();
	}
}
