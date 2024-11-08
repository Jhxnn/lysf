package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.CardDto;
import com.lysf.models.Card;
import com.lysf.repositories.CardRepository;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	AccountService accountService;

	public Card findByid(UUID id) {
		return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card cannot be found"));
	}

	public List<Card> findAll() {
		return cardRepository.findAll();
	}

	public Card createCard(CardDto cardDto) {
		var card = new Card();
		BeanUtils.copyProperties(cardDto, card);
		return cardRepository.save(card);
	}

	public boolean validation(CardDto cardDto) {
		if (cardRepository.findByCVV(cardDto.CVC()) != null
				&& cardRepository.findBySecurityCode(cardDto.securityCode()) != null) {
			
			return true;
		}
		return false;
	}

	public Card updateCard(UUID id, CardDto cardDto) {
		var card = findByid(id);
		if (cardDto.accountId() != null) {
			var account = accountService.findById(cardDto.accountId());
			card.setAccount(account);
		}
		if (cardDto.CVC() != null) {
			card.setCVV(cardDto.CVC());
		}
		if (cardDto.validDate() != null) {
			card.setValidDate(cardDto.validDate());
		}
		return cardRepository.save(card);
	}

	public void deleteCard(UUID id) {
		var card = findByid(id);
		cardRepository.delete(card);
	}
}
