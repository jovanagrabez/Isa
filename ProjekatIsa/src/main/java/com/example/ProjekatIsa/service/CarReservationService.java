package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.User;

@Service
public interface CarReservationService {
	
	public CarReservation save(CarReservation carReservation);	
	public List<CarReservation> findAllByUser(User user);
    public List<CarReservation> findAllByCar(Car car);

}
