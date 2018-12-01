package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.repository.CarRepository;

public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public Page<Car> findAll(Pageable page) {
		return carRepository.findAll(page);
	}

}
