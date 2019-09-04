package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.ProjekatIsa.model.Room;

@Service
public interface RoomService {

	List<Room> getAll();
	boolean deleteRoom(Room r);
}
