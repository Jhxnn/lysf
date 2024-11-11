	package com.lysf.models;

import java.time.LocalDate;
import java.util.UUID;

import com.lysf.models.enums.StatusInvoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID invoiceId;
	
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	private Double amount;
	
	private StatusInvoice status;
	
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;

	
	
	public Invoice(Account account, Double amount, StatusInvoice status, LocalDate dueDate,
			LocalDate issueDate) {
		this.account = account;
		this.amount = amount;
		this.status = status;
		this.dueDate = dueDate;
		this.issueDate = issueDate;
	}
	
	public Invoice() {
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public StatusInvoice getStatus() {
		return status;
	}

	public void setStatus(StatusInvoice status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public UUID getInvoiceId() {
		return invoiceId;
	}
	
	
}
