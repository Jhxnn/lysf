package com.lysf.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysf.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

}
