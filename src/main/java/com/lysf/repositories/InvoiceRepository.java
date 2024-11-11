package com.lysf.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lysf.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{
	
	 @Query("SELECT i FROM Invoice i WHERE i.account.id = :accountId AND i.issueDate < :pastDate AND i.dueDate >= :pastDate")
	  Invoice findInvoicesByAccountAndDateRange(
	            @Param("accountId") UUID accountId,
	            @Param("pastDate") LocalDate pastDate);
}
