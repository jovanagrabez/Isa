package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long>{

	Flight findOneById(Long id);
}
