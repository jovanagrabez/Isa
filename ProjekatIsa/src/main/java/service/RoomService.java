package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import model.Room;
import repository.RoomRepository;

public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	public Page<Room> findAll(Pageable page) {
		return roomRepository.findAll(page);
	}
}
