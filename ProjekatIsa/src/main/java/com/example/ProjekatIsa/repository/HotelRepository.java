package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	List<Hotel> findAll();
	Hotel findOneById(Long id);
	Hotel save(Hotel h);
}
