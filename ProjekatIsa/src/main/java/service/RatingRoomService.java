package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import model.RatingRoom;
import repository.RatingRoomRepository;

public class RatingRoomService {
	@Autowired
	private RatingRoomRepository ratingRoomRepository;
	
	public Page<RatingRoom> findAll(Pageable page) {
		return ratingRoomRepository.findAll(page);
	}
}
