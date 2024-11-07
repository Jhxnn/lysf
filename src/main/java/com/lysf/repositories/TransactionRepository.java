package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

}
