package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	List<Car> findAllByRentalcars(RentACar id);
	List<Car> findAllByFilijale(Filijale id);

	


}
