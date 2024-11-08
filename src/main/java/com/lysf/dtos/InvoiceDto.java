package com.lysf.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.lysf.models.enums.StatusInvoice;

public record InvoiceDto(UUID accountId, Double amount,StatusInvoice status, LocalDate dueDate, LocalDate issueDate) {

}
