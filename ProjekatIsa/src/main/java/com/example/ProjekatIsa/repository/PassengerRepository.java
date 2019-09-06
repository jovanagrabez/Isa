package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	

	Passenger findPassangerById(Long id);
}
