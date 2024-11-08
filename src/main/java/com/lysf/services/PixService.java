package com.lysf.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lysf.dtos.PixDto;
import com.lysf.models.Pix;
import com.lysf.repositories.PixRepository;

@Service
public class PixService {

	
	@Autowired
	PixRepository pixRepository;
	
	@Autowired
	AccountService accountService;
	
	
	public Pix findById(UUID id) {
		return pixRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found pix"));
	}
	public List<Pix> findAll(){
		return pixRepository.findAll();
	}
	public Pix createPix(PixDto pixDto) {
		var pix = new Pix();
		BeanUtils.copyProperties(pixDto, pix);
		return pixRepository.save(pix);
	}
	
	public Pix updatePix(UUID id, PixDto pixDto) {
		var pix = findById(id);
		if(pixDto.account() != null) {
			var account = accountService.findById(id);
			pix.setAccount(account);
		}
		if(pixDto.pixKey() != null) {
			pix.setPixKey(pixDto.pixKey());
		}
		return pixRepository.save(pix);
	}
	public boolean validationPix(PixDto pixDto) {
		if(pixRepository.findByPixKey(pixDto.pixKey()) != null) {
			return true;
		}
		return false;
	}
	
	public void deletePix(UUID id) {
		var pix = findById(id);
		pixRepository.delete(pix);
		
	}
}
