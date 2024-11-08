package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Pix;
import java.util.List;


public interface PixRepository extends JpaRepository<Pix, UUID>{
	
	public Pix findByPixKey(String pixKey);

}
