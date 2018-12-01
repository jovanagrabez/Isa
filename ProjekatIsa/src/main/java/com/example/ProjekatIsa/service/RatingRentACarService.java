package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.repository.RatingRentACarRepository;

public class RatingRentACarService {
	
	@Autowired
	private RatingRentACarRepository ratingRentACarRepository;
	
	public Page<RatingRentACar> findAll(Pageable page) {
		return ratingRentACarRepository.findAll(page);
	}

}
