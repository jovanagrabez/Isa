package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.User;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	FlightReservation findFlightReservationById(Long id);

	List<FlightReservation> findAllByUserId(Long id);

	FlightReservation findByFlightId(Long flight_id);
	
}
