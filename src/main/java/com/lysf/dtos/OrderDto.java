package com.lysf.dtos;

import java.util.UUID;

public record OrderDto(Double value, UUID methodId, String description) {

}
