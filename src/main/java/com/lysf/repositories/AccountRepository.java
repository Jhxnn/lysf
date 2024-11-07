package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
