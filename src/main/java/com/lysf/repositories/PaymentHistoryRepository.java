package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.PaymentHistory;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, UUID>{

}
