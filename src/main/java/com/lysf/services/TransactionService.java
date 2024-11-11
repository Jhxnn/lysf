package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.CardDto;
import com.lysf.dtos.PixDto;
import com.lysf.dtos.TransactionDto;
import com.lysf.models.Invoice;
import com.lysf.models.Transaction;
import com.lysf.models.enums.PaymentType;
import com.lysf.models.enums.StatusInvoice;
import com.lysf.models.enums.StatusTransaction;
import com.lysf.repositories.AccountRepository;
import com.lysf.repositories.InvoiceRepository;
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

	@Autowired
	PixService pixService;

	@Autowired
	EmailService emailService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	InvoiceService invoiceService;

	public Transaction findById(UUID id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("transaction cannot be found"));
	}

	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	public Transaction createTransaction(UUID id, PaymentType payType, TransactionDto transactionDto, CardDto cardDto,
			PixDto pixDto) {
		var account = accountService.findById(id);
		if (account.getBalance() < transactionDto.value()) {
			return null;
		}
		if (payType == PaymentType.CARD) {
			if (cardService.validation(cardDto) == true) {
				if (transactionDto.credit() == true) {
					var transaction = new Transaction();
					BeanUtils.copyProperties(transactionDto, transaction);
					var invoice = invoiceService.findMonthlyInvoice(account.getIdAccount(), transactionDto.date());
					if (invoice == null) {
						Invoice invoice1 = new Invoice(account, transactionDto.value(), StatusInvoice.PENDING,
								transactionDto.date(), transactionDto.date().plusDays(30));
						invoiceRepository.save(invoice1);

					} else {
						invoice.setAmount(invoice.getAmount() + transactionDto.value());
					}

					emailService.sendTransactionEmail(account.getUser().getEmail(), "Credit transaction",
							"Transaction with a value of " + transactionDto.value() + "R$" + ", on your card");

					return transactionRepository.save(transaction);
				} else {
					var transaction = new Transaction();
					BeanUtils.copyProperties(transactionDto, transaction);
					account.setBalance(account.getBalance() - transactionDto.value());

					emailService.sendTransactionEmail(account.getUser().getEmail(), "Debit transaction",
							"Transaction with a value of " + transactionDto.value() + "R$" + ", on your card");

					return transactionRepository.save(transaction);
				}
			} else {

				var transaction = new Transaction();
				BeanUtils.copyProperties(transactionDto, transaction);
				transaction.setStatus(StatusTransaction.CANCELED);
				return transactionRepository.save(transaction);
			}
		}
		if (payType == PaymentType.PIX) {
			if (pixService.validationPix(pixDto) == true) {
				var transaction = new Transaction();
				BeanUtils.copyProperties(transactionDto, transaction);
				account.setBalance(account.getBalance() - transactionDto.value());
				emailService.sendTransactionEmail(account.getUser().getEmail(), "Transaction",
						"Transaction with a value of " + transactionDto.value() + "R$" + ", with pix");
				return transactionRepository.save(transaction);

			} else {

				var transaction = new Transaction();
				BeanUtils.copyProperties(transactionDto, transaction);
				transaction.setStatus(StatusTransaction.CANCELED);
				return transactionRepository.save(transaction);
			}

		}
		return null;

	}

	public Transaction refund(UUID transactionId) {
		var transaction = findById(transactionId);
		var account = transaction.getAccount();
		account.setBalance(account.getBalance() + transaction.getValue());
		transaction.setStatus(StatusTransaction.REFUND);
		accountRepository.save(account);
		emailService.sendTransactionEmail(account.getUser().getEmail(), "Transaction refund",
				"Transaction with a value of " + transaction.getValue() + "R$" + ", was refunded");
		return transactionRepository.save(transaction);
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
