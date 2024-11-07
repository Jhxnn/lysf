package com.lysf.models;

import java.util.UUID;

import com.lysf.models.enums.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name ="paymentMethod")
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID paymentMethodId;
	
	
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	
	private PaymentType type;
	
	private String details;

	public PaymentType getType() {
		return type;
	}

	
	public void setType(PaymentType type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public UUID getPaymentMethodId() {
		return paymentMethodId;
	}
	
	
}
