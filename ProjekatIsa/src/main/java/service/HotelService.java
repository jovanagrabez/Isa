package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import model.Hotel;
import repository.HotelRepository;
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Page<Hotel> findAll(Pageable page) {
		return hotelRepository.findAll(page);
	}

	
}
