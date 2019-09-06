package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.SeatArrangement;

@Repository
public interface SeatArrangementRepository  extends JpaRepository<SeatArrangement, Long>{
	
	 SeatArrangement findSeatArrangementById(Long id);
	 SeatArrangement findSeatArrangementByName(String name);

}
