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

	@Override
	public boolean deleteHotel(Hotel h) {
		// TODO Auto-generated method stub
		try {
			hotelRepository.delete(h);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Hotel findOneById(Long id) {
		// TODO Auto-generated method stub
		return hotelRepository.findOneById(id);
	}

	@Override
	public List<Hotel> findAllByCity(String city) {
		// TODO Auto-generated method stub
		return hotelRepository.findAllByCity(city);
	}
	

}
