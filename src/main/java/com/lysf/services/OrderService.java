package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.OrderDto;
import com.lysf.models.Order;
import com.lysf.repositories.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	PaymentMethodService payMethodService;
	
	public Order findById(UUID id) {
		return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found order"));
	}
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	public Order createOrder(OrderDto orderDto) {
		var order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		return orderRepository.save(order);
	}
	public Order updateOrder(UUID id, OrderDto orderDto) {
		var order = findById(id);
		if(orderDto.methodId() != null) {
			var method = payMethodService.findById(orderDto.methodId());
			order.setMethod(method);
		}
		if(orderDto.description() != null) {
			order.setDescription(orderDto.description());
		}
		if(orderDto.value() != null) {
			order.setValue(orderDto.value());
		}
		return orderRepository.save(order);
	}
	public void deleteOrder(UUID id) {
		var order = findById(id);
		orderRepository.delete(order);
	}
}
