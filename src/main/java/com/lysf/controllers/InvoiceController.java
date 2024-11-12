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

import com.lysf.dtos.InvoiceDto;
import com.lysf.models.Invoice;
import com.lysf.services.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	
	@Autowired
	InvoiceService invoiceService;
	

	@GetMapping("/{id}")
	@Operation(description = "Busca por ID")
	public ResponseEntity<Invoice> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(invoiceService.findById(id));
	}
	@GetMapping("/all")
	@Operation(description = "Busca todos")
	public ResponseEntity<List<Invoice>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(invoiceService.findAll());
	}
	@PostMapping
	@Operation(description = "Cria uma fatura")
	public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDto invoiceDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoiceDto));
	}
	@PutMapping("/{id}")
	@Operation(description = "Atualiza uma fatura")
	ResponseEntity<Invoice> updateInvoice(@PathVariable(name = "id")UUID id,
			@RequestBody InvoiceDto invoiceDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.updateInvoice(id, invoiceDto));
	}
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta uma fatura")
	ResponseEntity<Invoice> deleteInvoice(@PathVariable(name = "id")UUID id){
		invoiceService.deleteInvoice(id);
		return ResponseEntity.noContent().build();
	}
}
