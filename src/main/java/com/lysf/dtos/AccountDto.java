package com.lysf.dtos;

import java.util.UUID;

import com.lysf.models.enums.AccountType;
import com.lysf.models.enums.StatusAccount;

public record AccountDto(UUID userId, Double balance ,AccountType type ,StatusAccount status) {

}
