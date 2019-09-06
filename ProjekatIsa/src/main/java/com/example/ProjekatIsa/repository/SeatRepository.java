package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

	Seat findSeatById(Long id);
}
