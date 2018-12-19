package com.example.ProjekatIsa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.HotelRepository;
@Service
public interface HotelService {

	List<Hotel> getAll();
	
	
}
