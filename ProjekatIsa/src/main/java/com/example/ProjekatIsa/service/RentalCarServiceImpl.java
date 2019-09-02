package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.RentalCarRepository;
@Service
public class RentalCarServiceImpl implements RentalCarService {
	@Autowired
	private RentalCarRepository rentalcarRepository;

	@Override
	public List<RentACar> getAll() {
		// TODO Auto-generated method stub
		return rentalcarRepository.findAll();
	}

	@Override
	public RentACar findOneById(Long id) {
		// TODO Auto-generated method stub
		return rentalcarRepository.findOneById(id);
	}

}
