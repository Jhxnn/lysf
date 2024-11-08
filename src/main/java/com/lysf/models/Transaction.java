	package com.lysf.models;

import java.time.LocalDate;
import java.util.UUID;

import com.lysf.models.enums.StatusTransaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private UUID transactionId;
	
	private Double value;
	
	private StatusTransaction status;
	
	@JoinColumn(name ="method_id",referencedColumnName = "id")
	private PaymentMethod method;
	
	private LocalDate date;	
	
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public UUID getTransactionId() {
		return transactionId;
	}


	public StatusTransaction getStatus() {
		return status;
	}

	public void setStatus(StatusTransaction status) {
		this.status = status;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}
	
	
}
