package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	List<Car> findAllByRentacar(RentACar id);
	List<Car> findAllByFilijale(Filijale id);
	Car findOneById(Long id);
	List<Car> findAllByDiscount(Discount discount);
	//List<Car> findAllByRentalcars(Car h);
	List<Car> findAllByRentacar(Car h);
	Car save(Car car);
	List<Car> findAll();

	


}
