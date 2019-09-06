package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.FlightReservation;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	FlightReservation findFlightReservationById(Long id);
	
}
