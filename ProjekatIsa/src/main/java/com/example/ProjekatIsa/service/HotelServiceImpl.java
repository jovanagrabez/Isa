package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.HotelRepository;

@Service
public class  HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel addHotel(Hotel h) {
		// TODO Auto-generated method stub
		return hotelRepository.save(h);
	}
	

}
