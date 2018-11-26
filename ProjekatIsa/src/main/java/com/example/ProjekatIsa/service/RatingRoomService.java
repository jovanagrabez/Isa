package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.repository.RatingRoomRepository;

public class RatingRoomService {
	@Autowired
	private RatingRoomRepository ratingRoomRepository;
	
	public Page<RatingRoom> findAll(Pageable page) {
		return ratingRoomRepository.findAll(page);
	}
}
