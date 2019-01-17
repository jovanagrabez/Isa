package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.RatingRentACarRepository;

public interface RatingRentACarService {
	
	List<RentACar> getAll();

}
