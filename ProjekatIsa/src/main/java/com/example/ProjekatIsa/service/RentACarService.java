package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.RentACarRepository;

public class RentACarService {
	
	@Autowired
	private RentACarRepository rentacarRepository;
	
	public Page<RentACar> findAll(Pageable page) {
		return rentacarRepository.findAll(page);
	}

}
