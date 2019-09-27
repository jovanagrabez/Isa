package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.PricingCar;

@Service
public interface PricingCarService {
	List<PricingCar> findAllByCar(Car c);
	List<PricingCar> findAll();
	PricingCar save(PricingCar pricing);
	PricingCar findOneById(Long id);
	boolean deletePricing(PricingCar pricing);

}
