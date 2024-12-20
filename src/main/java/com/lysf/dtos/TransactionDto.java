package com.lysf.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.lysf.models.enums.StatusTransaction;

public record TransactionDto(Double value,UUID methodId,LocalDate date,
		UUID accountId, boolean credit) {

}
