package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RentACar;

@Service
public interface RatingRentACarService {
	
	List<RatingCar> getAll();

}
