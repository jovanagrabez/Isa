package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	
	@Override
	public List<Room> getAll() {
		return roomRepository.findAll();
	}


	@Override
	public boolean deleteRoom(Room r) {
		try {
			roomRepository.delete(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Room findOneById(Long id) {
		// TODO Auto-generated method stub
		return roomRepository.findOneById(id);
	}


	@Override
	public List<Room> findAllByHotel(Hotel h) {
		// TODO Auto-generated method stub
		return roomRepository.findAllByHotel(h);
	}


	@Override
	public Room addRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

}
