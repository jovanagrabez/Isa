package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.PricingCar;

@Repository
public interface PricingCarRepository extends JpaRepository<PricingCar, Long>{
	List<PricingCar> findAll();
	List<PricingCar> findAllByCar(Car c);

}
