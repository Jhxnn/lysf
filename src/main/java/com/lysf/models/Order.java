package com.lysf.models;

import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID orderId;
	
	private double value;
	
	@JoinColumn(name = "method_id", referencedColumnName = "id")
	private PaymentMethod method;
	
	private String description;

	public UUID getOrderId() {
		return orderId;
	}

	

	public PaymentMethod getMethod() {
		return method;
	}



	public void setMethod(PaymentMethod method) {
		this.method = method;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}
	
	
}
