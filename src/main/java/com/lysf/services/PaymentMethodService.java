package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.PaymentMethodDto;
import com.lysf.models.PaymentMethod;
import com.lysf.repositories.PaymentMethodRepository;

@Service
public class PaymentMethodService {

	@Autowired
	PaymentMethodRepository payMethodRepository;

	@Autowired
	AccountService accountService;

	public PaymentMethod findById(UUID id) {
		return payMethodRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be payment method"));
	}

	public List<PaymentMethod> findAll() {
		return payMethodRepository.findAll();
	}

	public PaymentMethod createMethod(PaymentMethodDto payMethodDto) {
		var payMethod = new PaymentMethod();
		BeanUtils.copyProperties(payMethodDto, payMethod);
		return payMethodRepository.save(payMethod);
	}

	public PaymentMethod updateMethod(UUID id, PaymentMethodDto payMethodDto) {
		var payMethod = findById(id);
		if (payMethodDto.accountId() != null) {
			var account = accountService.findById(payMethodDto.accountId());
			payMethod.setAccount(account);
		}
		if (payMethodDto.details() != null) {
			payMethod.setDetails(payMethodDto.details());
		}
		if (payMethodDto.type() != null) {
			payMethod.setType(payMethodDto.type());
		}
		return payMethodRepository.save(payMethod);
	}

	public void deleteMethod(UUID id) {
		var payMethod = findById(id);
		payMethodRepository.save(payMethod);
	}
}
