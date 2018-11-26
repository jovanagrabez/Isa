package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.repository.AviocompanyRepository;

public class AviocompanyService {

	@Autowired
	private AviocompanyRepository avioRepository;
	
	public Page<Aviocompany> findAll(Pageable page) {
		return avioRepository.findAll(page);
	}

	
}
