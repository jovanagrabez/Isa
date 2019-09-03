package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.repository.FilijaleRepository;

@Service
public class FilijaleServiceImpl implements FilijaleService {

	@Autowired
	FilijaleRepository filRepository;
	@Override
	public Filijale addFilijale(Filijale f) {
		// TODO Auto-generated method stub
		return filRepository.save(f);
	}

}
