package com.example.ProjekatIsa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long>{

	Flight findOneById(Long id);


}
