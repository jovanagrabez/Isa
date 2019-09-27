package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarReservationRepository;

@Service
public class CarReservationServiceImpl implements CarReservationService {
	
	@Autowired
	CarReservationRepository carResRepository;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CarReservation save(CarReservation carReservation) {
		// TODO Auto-generated method stub
		return carResRepository.save(carReservation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarReservation> findAllByUser(User user) {
		// TODO Auto-generated method stub
		return carResRepository.findAllByUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarReservation> findAllByCar(Car car) {
		// TODO Auto-generated method stub
		return carResRepository.findAllByCar(car);
	}

}
