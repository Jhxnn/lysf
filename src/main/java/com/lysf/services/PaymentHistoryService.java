package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.PaymentHistoryDto;
import com.lysf.models.PaymentHistory;
import com.lysf.repositories.PaymentHistoryRepository;

@Service
public class PaymentHistoryService {

	@Autowired
	PaymentHistoryRepository payHistRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	TransactionService transactionService;
	
	public PaymentHistory findById(UUID id) {
		return payHistRepository.findById(id).orElseThrow(()-> new RuntimeException("payment history cannot be found"));
	}
	public List<PaymentHistory> findAll(){
		return payHistRepository.findAll();
	}
	public PaymentHistory createPaymentHistory(PaymentHistoryDto payHisDto) {
		var payHis = new PaymentHistory();
		BeanUtils.copyProperties(payHisDto, payHis);
		return payHistRepository.save(payHis);
	}
	public PaymentHistory updatePayHis(UUID id, PaymentHistoryDto payHisDto) {
		var payHis = findById(id);
		if(payHisDto.accountId() != null) {
			var account = accountService.findById(payHisDto.accountId());
			payHis.setAccount(account);
		}
		if(payHisDto.amount() != null) {
			payHis.setAmount(payHisDto.amount());
		}
		if(payHisDto.date() != null) {
			payHis.setDate(payHisDto.date());
		}
		if(payHisDto.methodId() != null) {
			var method = paymentMethodService.findById(id);
			payHis.setMethod(method);
		}
		if(payHisDto.transactionId() != null) {
			var transaction = transactionService.findById(payHisDto.transactionId());
			payHis.setTransaction(transaction);
		
		}
		if(payHisDto.status() != null) {
			payHis.setStatus(payHisDto.status());
		}
		return payHistRepository.save(payHis);
	}
	public void deletePayHis(UUID id) {
		var payHis = findById(id);
		payHistRepository.delete(payHis);
	}
}
