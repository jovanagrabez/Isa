package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.repository.CarRepository;

@Service
public interface CarService {
	Car save(Car c);
	Car addCar(Car c);
	Car findOneById(Long id);
	List<Car> getAll();
}
