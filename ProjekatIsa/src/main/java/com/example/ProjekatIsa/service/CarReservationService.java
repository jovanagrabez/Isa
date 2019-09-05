package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.CarReservation;

@Service
public interface CarReservationService {

	
	List<CarReservation> allCarReservation(Long id);

}
