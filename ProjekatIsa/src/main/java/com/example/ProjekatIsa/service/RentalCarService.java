package com.example.ProjekatIsa.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.RentACar;

@Service
public interface RentalCarService {
	List<RentACar> getAll();

}
