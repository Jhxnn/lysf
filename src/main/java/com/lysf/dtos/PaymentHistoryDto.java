package com.lysf.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.lysf.models.enums.StatusTransaction;

public record PaymentHistoryDto(UUID accountId, UUID methodId,
		LocalDate date,StatusTransaction status, Double amount, UUID transactionId) {

}
