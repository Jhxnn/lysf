package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>{

}
