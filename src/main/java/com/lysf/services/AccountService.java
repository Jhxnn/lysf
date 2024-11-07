package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.AccountDto;
import com.lysf.models.Account;
import com.lysf.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserService userService;

	public Account findById(UUID id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account cannot be found"));
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account createAccount(AccountDto accountDto) {
		var account = new Account();
		BeanUtils.copyProperties(accountDto, account);
		return accountRepository.save(account);
	}

	public Account updateAccount(UUID id, AccountDto accountDto) {
		var account = findById(id);

		if (accountDto.userId() != null) {
			var user = userService.findById(accountDto.userId());
			account.setUser(user);
		}
		if (accountDto.balance() != null) {
			account.setBalance(accountDto.balance());
		}
		if (accountDto.status() != null) {
			account.setStatus(accountDto.status());
		}
		if (accountDto.type() != null) {
			account.setType(accountDto.type());
		}

		return accountRepository.save(account);
	}

	public void deleteAccount(UUID id) {
		var account = findById(id);
		accountRepository.delete(account);

	}
}
