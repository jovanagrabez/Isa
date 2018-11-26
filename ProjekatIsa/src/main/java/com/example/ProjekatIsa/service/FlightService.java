package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.repository.FlightRepository;

public class FlightService {

	@Autowired
	private FlightRepository flightRepository;
	
	public Page<Flight> findAll(Pageable page) {
		return flightRepository.findAll(page);
	}

	
}
