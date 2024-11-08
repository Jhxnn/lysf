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
@Table(name = "paymentsHistory")
public class PaymentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID paymentHistoryId;
	
	
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	@JoinColumn(name = "method_id", referencedColumnName = "id")
	private PaymentMethod method;
	
	private LocalDate date;
	
	private StatusTransaction status;
	
	private Double amount;
	
	@JoinColumn(name = "transaction_id", referencedColumnName = "id")
	private Transaction transaction;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public StatusTransaction getStatus() {
		return status;
	}

	public void setStatus(StatusTransaction status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public UUID getPaymentHistoryId() {
		return paymentHistoryId;
	}
	
	
}
