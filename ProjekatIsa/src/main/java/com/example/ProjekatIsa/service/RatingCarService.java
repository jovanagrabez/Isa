package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.RatingCar;
//import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;

@Service
public class RatingCarService {
	
	@Autowired
	private RatingCarRepository ratingCarRepository;
	
	public List<RatingCar> getAll() {
		return ratingCarRepository.findAll();
	}

	public RatingCar save(RatingCar dodavanje) {
		// TODO Auto-generated method stub
		return ratingCarRepository.save(dodavanje);
	}
	
	public RatingCar addRC(RatingCar rc) {
		return ratingCarRepository.save(rc);

	}
}


