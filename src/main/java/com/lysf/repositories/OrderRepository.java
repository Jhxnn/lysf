package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
