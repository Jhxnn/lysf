package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.InvoiceDto;
import com.lysf.models.Invoice;
import com.lysf.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	AccountService accountService;
	
	public Invoice findById(UUID id) {
		return invoiceRepository.findById(id).orElseThrow(()-> new RuntimeException("Invoice cannot be found"));
	}
	public List<Invoice> findAll(){
		return invoiceRepository.findAll();
	}
	public Invoice createInvoice(InvoiceDto invoiceDto) {
		var invoice = new Invoice();
		BeanUtils.copyProperties(invoiceDto, invoice);
		return invoiceRepository.save(invoice);
	}
	public Invoice updateInvoice(UUID id, InvoiceDto invoiceDto) {
		var invoice = findById(id);
		if(invoiceDto.accountId() != null) {
			var account = accountService.findById(invoiceDto.accountId());
			invoice.setAccount(account);
		}
		if(invoiceDto.amount() != null) {
			invoice.setAmount(invoiceDto.amount());
		}
		if(invoiceDto.dueDate() != null) {
			invoice.setDueDate(invoiceDto.dueDate());
		}
		if(invoiceDto.issueDate() != null) {
			invoice.setIssueDate(invoiceDto.issueDate());
		}
		return invoiceRepository.save(invoice);
	}
	public void deleteInvoice(UUID id) {
		var invoice = findById(id);
		invoiceRepository.delete(invoice);
	}
}
