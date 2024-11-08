package com.lysf.dtos;

import java.util.UUID;

import com.lysf.models.enums.PaymentType;

public record PaymentMethodDto(UUID accountId, PaymentType type, String details) {

}
