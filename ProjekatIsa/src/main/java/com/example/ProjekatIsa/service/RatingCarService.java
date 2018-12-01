package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.repository.RatingCarRepository;

public class RatingCarService {
	
	@Autowired
	private RatingCarRepository ratingCarRepository;
	
	public Page<RatingCar> findAll(Pageable page) {
		return ratingCarRepository.findAll(page);
	}

}
