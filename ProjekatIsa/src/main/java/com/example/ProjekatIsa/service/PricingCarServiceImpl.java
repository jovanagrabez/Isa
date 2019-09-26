package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.PricingCar;
import com.example.ProjekatIsa.repository.PricingCarRepository;

@Service
public class PricingCarServiceImpl implements PricingCarService {

	
	@Autowired
	private PricingCarRepository pricingRepository;
	
	@Override
	public List<PricingCar> findAllByCar(Car c) {
		// TODO Auto-generated method stub
		return pricingRepository.findAllByCar(c);
	}

	@Override
	public List<PricingCar> findAll() {
		// TODO Auto-generated method stub
		return pricingRepository.findAll();
	}

}
