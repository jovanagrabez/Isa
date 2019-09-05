package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.repository.CarReservationRepository;

@Service
public class CarReservationServiceImpl implements CarReservationService {
	
	@Autowired
	CarReservationRepository carresRepository;

	@Override
	public List<CarReservation> allCarReservation(Long id) {
		
		// TODO Auto-generated method stub
		return carresRepository.findAllByCar(id);
	}

}
