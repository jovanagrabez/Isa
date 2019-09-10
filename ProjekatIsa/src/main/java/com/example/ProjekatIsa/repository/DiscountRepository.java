package com.example.ProjekatIsa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.RentACar;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

	Discount findOneById(Long id);

	ArrayList<Discount> findAllByRentACar(RentACar rent);

	List<Car> findAllCarByRentACar(RentACar rent);
	Discount findOneByCar(Car car);

}
