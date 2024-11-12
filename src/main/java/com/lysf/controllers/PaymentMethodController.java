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
import com.lysf.dtos.PaymentMethodDto;
import com.lysf.models.PaymentMethod;
import com.lysf.services.PaymentMethodService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {

	@Autowired
	PaymentMethodService payMethodService;
	
	@GetMapping("/{id}")
	@Operation(description = "Busca por ID")
	public ResponseEntity<PaymentMethod> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(payMethodService.findById(id));
	}
	@GetMapping("/all")
	@Operation(description = "Busca todos")
	public ResponseEntity<List<PaymentMethod>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(payMethodService.findAll());
	}
	@PostMapping
	@Operation(description = "Cria um methodo de pagamento")
	public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethodDto payMethodDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payMethodService.createMethod(payMethodDto));
	}
	@PutMapping("/{id}")
	@Operation(description = "Atualiza o metodo de pagamento")
	ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable(name = "id")UUID id,
			@RequestBody PaymentMethodDto payMethodDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(payMethodService.updateMethod(id, payMethodDto));
	}
	@DeleteMapping("/{id}")
	@Operation(description = "deleta o metodo de pagamento")
	ResponseEntity<PaymentMethod> deletePaymentMethod(@PathVariable(name = "id")UUID id){
		payMethodService.deleteMethod(id);
		return ResponseEntity.noContent().build();
	}
	
}
