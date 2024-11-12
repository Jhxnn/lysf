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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/payment-history")
public class PaymentHistoryController {

	@Autowired
	PaymentHistoryService payHisService;
	

	@GetMapping("/{id}")
	@Operation(description = "Busca por ID")
	public ResponseEntity<PaymentHistory> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(payHisService.findById(id));
	}
	@GetMapping("/all")
	@Operation(description = "Busca todos")
	public ResponseEntity<List<PaymentHistory>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(payHisService.findAll());
	}
	@PostMapping
	@Operation(description = "Cria um historico de pagamento")
	public ResponseEntity<PaymentHistory> createPaymentHistory(@RequestBody PaymentHistoryDto payHisDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payHisService.createPaymentHistory(payHisDto));
	}
	@PutMapping("/{id}")
	@Operation(description = "Atualiza um historico de pagamento")
	ResponseEntity<PaymentHistory> updatePaymentHistory(@PathVariable(name = "id")UUID id,
			@RequestBody PaymentHistoryDto payHisDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payHisService.updatePayHis(id, payHisDto));
	}
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta historico de pagamento")
	ResponseEntity<PaymentHistory> deletePaymentHistory(@PathVariable(name = "id")UUID id){
		payHisService.deletePayHis(id);
		return ResponseEntity.noContent().build();
	}
}
