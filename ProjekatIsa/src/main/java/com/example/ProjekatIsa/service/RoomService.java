package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.RoomRepository;

public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	public Page<Room> findAll(Pageable page) {
		return roomRepository.findAll(page);
	}
}
