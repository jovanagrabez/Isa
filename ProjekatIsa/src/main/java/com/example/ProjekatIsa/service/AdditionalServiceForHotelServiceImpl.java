package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.repository.AdditionalServiceForHotelRepository;

@Service
public class AdditionalServiceForHotelServiceImpl implements AdditionalServiceForHotelService {

	@Autowired
	private AdditionalServiceForHotelRepository rep;
	
	@Override
	public List<AdditionalServiceForHotel> getAll() {
		// TODO Auto-generated method stub
		return rep.findAll();
	}

	@Override
	public AdditionalServiceForHotel addService(AdditionalServiceForHotel a) {
		// TODO Auto-generated method stub
		return rep.save(a);
	}

	@Override
	public boolean deleteAdditionalServiceForHotel(AdditionalServiceForHotel a) {
		try {
			rep.delete(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
