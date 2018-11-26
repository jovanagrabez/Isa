package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.DestinationRepository;
import com.example.ProjekatIsa.repository.HotelRepository;

public class DestinationService {

	@Autowired
	private DestinationRepository destinationRepository;
	
	public Page<Destination> findAll(Pageable page) {
		return destinationRepository.findAll(page);
	}

	
}
