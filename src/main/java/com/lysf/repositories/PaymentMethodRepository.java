package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.PaymentMethod;
import com.lysf.models.Account;
import java.util.List;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>{

	public List<PaymentMethod> findByAccount(Account account);
}
