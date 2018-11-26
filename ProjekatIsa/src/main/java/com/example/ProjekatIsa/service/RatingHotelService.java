package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.repository.RatingHotelRepository;

public class RatingHotelService {
	@Autowired
	private RatingHotelRepository ratingHotelRepository;
	
	public Page<RatingHotel> findAll(Pageable page) {
		return ratingHotelRepository.findAll(page);
	}
}
