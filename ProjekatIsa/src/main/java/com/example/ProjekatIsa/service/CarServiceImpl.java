package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.repository.CarRepository;
@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	CarRepository carRepository;

	@Override
	public Car save(Car c) {
		// TODO Auto-generated method stub
		return carRepository.save(c);
	}

	@Override
	public Car addCar(Car c) {
		// TODO Auto-generated method stub
		return carRepository.save(c);
	}

	@Override
	public Car findOneById(Long id) {
		// TODO Auto-generated method stub
		return carRepository.findOneById(id);
	}

}
