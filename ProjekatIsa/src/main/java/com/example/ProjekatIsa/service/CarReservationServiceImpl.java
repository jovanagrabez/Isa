package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.repository.CarReservationRepository;

@Service
public class CarReservationServiceImpl implements CarReservationService {
	
	@Autowired
	CarReservationRepository carResRepository;

	@Override
	public CarReservation save(CarReservation carReservation) {
		// TODO Auto-generated method stub
		return carResRepository.save(carReservation);
	}

}
