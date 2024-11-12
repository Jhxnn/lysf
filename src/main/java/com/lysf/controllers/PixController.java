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
import com.lysf.dtos.PixDto;
import com.lysf.models.Pix;
import com.lysf.services.PixService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pix")
public class PixController {


	@Autowired
	PixService pixService;
	

	@GetMapping("/{id}")
	@Operation(description = "Busca por ID")
	public ResponseEntity<Pix> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pixService.findById(id));
	}
	@GetMapping("/all")
	@Operation(description = "Busca todos")
	public ResponseEntity<List<Pix>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(pixService.findAll());
	}
	@PostMapping
	@Operation(description = "Cria um pix")
	public ResponseEntity<Pix> createPix(@RequestBody PixDto pixDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(pixService.createPix(pixDto));
	}
	@PutMapping("/{id}")
	@Operation(description = "Atualiza")
	ResponseEntity<Pix> updatePix(@PathVariable(name = "id")UUID id,
			@RequestBody PixDto pixDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(pixService.updatePix(id, pixDto));
	}
	@DeleteMapping("/{id}")
	@Operation(description = "Deleta")
	ResponseEntity<Pix> deletePix(@PathVariable(name = "id")UUID id){
		pixService.deletePix(id);
		return ResponseEntity.noContent().build();
	}
}
