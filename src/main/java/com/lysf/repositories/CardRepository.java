package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Card;
import java.util.List;


public interface CardRepository extends JpaRepository<Card, UUID>{

	public Card findByCVV(Integer CVV);
	public Card findBySecurityCode(String securityCode);

}
