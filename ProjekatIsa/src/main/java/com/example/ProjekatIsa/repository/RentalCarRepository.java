package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;

public interface RentalCarRepository extends JpaRepository<RentACar, Long> {
	List<RentACar> findAll();
	RentACar findOneById(Long id);
	RentACar findOneByCar(Car car);
	RentACar findOneByFilijale(Filijale fil);
 

}
