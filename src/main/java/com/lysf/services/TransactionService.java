package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.CardDto;
import com.lysf.dtos.TransactionDto;
import com.lysf.models.PaymentMethod;
import com.lysf.models.Transaction;
import com.lysf.models.enums.PaymentType;
import com.lysf.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Autowired
	AccountService accountService;

	@Autowired
	CardService cardService;

	public Transaction findById(UUID id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("transaction cannot be found"));
	}

	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	public Transaction createTransaction(UUID id, PaymentType payType, TransactionDto transactionDto, CardDto cardDto) {
		var account = accountService.findById(id);
		if (payType == PaymentType.CARD) {
			if (cardService.validation(cardDto) == true) {
				var transaction = new Transaction();
				BeanUtils.copyProperties(transactionDto, transaction);
				return transactionRepository.save(transaction);
			} else {
				new RuntimeException("cannot be validated");
				return null;
			}
		}
		
		/*terminar  de arrumar (verificar boleto, verificar, pix) */
		
		return null;

	}

	public Transaction updateTransaction(UUID id, TransactionDto transactionDto) {
		var transaction = findById(id);
		if (transactionDto.accountId() != null) {
			var account = accountService.findById(id);
			transaction.setAccount(account);
		}
		if (transactionDto.date() != null) {
			transaction.setDate(transactionDto.date());

		}
		if (transactionDto.methodId() != null) {
			var method = paymentMethodService.findById(id);
			transaction.setMethod(method);
		}
		if (transactionDto.status() != null) {
			transaction.setStatus(transactionDto.status());
		}
		if (transactionDto.value() != null) {
			transaction.setValue(transactionDto.value());
		}
		return transactionRepository.save(transaction);
	}

	public void deleteTransaction(UUID id) {
		var transaction = findById(id);
		transactionRepository.delete(transaction);
	}
}
