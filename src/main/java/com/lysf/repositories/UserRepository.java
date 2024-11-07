package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}
