package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.RentACar;

public interface RentalCarRepository extends JpaRepository<RentACar, Long> {
	List<RentACar> findAll();
	RentACar findOneById(Long id);
 

}
