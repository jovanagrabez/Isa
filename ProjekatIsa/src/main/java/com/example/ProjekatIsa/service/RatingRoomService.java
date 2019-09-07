package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.repository.RatingRoomRepository;
@Service
public class RatingRoomService {
	@Autowired
	private RatingRoomRepository ratingRoomRepository;
	
	public List<RatingRoom> findAll() {
		return ratingRoomRepository.findAll();
	}
}
