package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Flight;


public interface AviocompanyRepository extends JpaRepository<Aviocompany, Long> {

	Aviocompany findOneById(Long id);
	Aviocompany findAviocompanyByFlightId(Long id);
	Aviocompany findOneByFlight(Flight flight);
}
