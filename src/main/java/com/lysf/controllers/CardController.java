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
import com.lysf.models.Card;
import com.lysf.services.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	CardService cardService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Card> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(cardService.findByid(id));
	}
	@GetMapping("/all")
	public ResponseEntity<List<Card>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(cardService.findAll());
	}
	@PostMapping
	public ResponseEntity<Card> createCard(@RequestBody CardDto cardDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(cardDto));
	}
	@PutMapping("/{id}")
	ResponseEntity<Card> updateCard(@PathVariable(name = "id")UUID id,
			@RequestBody CardDto cardDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(cardService.updateCard(id, cardDto));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<Card> deleteCard(@PathVariable(name = "id")UUID id){
		cardService.deleteCard(id);
		return ResponseEntity.noContent().build();
	}
}
