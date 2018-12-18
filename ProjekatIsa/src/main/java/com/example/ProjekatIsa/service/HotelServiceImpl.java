package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.HotelRepository;

public class  HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

}
