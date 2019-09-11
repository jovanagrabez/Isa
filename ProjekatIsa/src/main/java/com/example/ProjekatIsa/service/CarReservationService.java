package com.example.ProjekatIsa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.CarReservation;

@Service
public interface CarReservationService {
	
	@Transactional
	public CarReservation save(CarReservation carReservation);

}
