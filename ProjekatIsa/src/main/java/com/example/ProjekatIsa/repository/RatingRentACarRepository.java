package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.User;

@Repository
public interface RatingRentACarRepository extends JpaRepository<RatingRentACar, Long> {
	
	List<RatingRentACar> findAll();

	List<RatingRentACar> findAllByUser(User user);

}