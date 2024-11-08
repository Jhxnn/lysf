package com.lysf.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CardDto(UUID accountId, Integer CVC, String securityCode, LocalDate validDate) {

}
