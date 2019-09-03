package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	List<Room> findAll();
	List<Room> findAllByHotel(Hotel h);
}
