package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Card;

public interface CardRepository extends JpaRepository<Card, UUID>{

}
