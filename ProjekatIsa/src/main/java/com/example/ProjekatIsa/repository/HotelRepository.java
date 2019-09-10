package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	List<Hotel> findAll();
	Hotel findOneById(Long id);
	Hotel save(Hotel h);
	Hotel findOneByRooms(Room rooms);
	List<Hotel> findAllByCity(String city);
}
