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
import com.lysf.dtos.OrderDto;
import com.lysf.models.Order;
import com.lysf.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
	}
	@GetMapping("/all")
	public ResponseEntity<List<Order>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
	}
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
	}
	@PutMapping("/{id}")
	ResponseEntity<Order> updateOrder(@PathVariable(name = "id")UUID id,
			@RequestBody OrderDto orderDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrder(id, orderDto));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<Order> deleteOrder(@PathVariable(name = "id")UUID id){
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}


}
