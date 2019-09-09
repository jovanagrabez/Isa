package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.User;

public interface RatingCarRepository extends JpaRepository<RatingCar, Long> {
	
	List<RatingCar> findAllByCar(Long id);
	List<RatingCar> findAllByUser(Long id);
	List<RatingCar> findAllByUser(User user);
	List<RatingCar> findAllByCar(Car car);
	

}
