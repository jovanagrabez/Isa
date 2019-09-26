package com.example.ProjekatIsa.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.PricingRepository;


@Service
public class PricingServiceImpl implements PricingService{

	@Autowired
	private PricingRepository pricingRepository;
	
	@Override
	public List<Pricing> findAllByRoom(Room r) {
		// TODO Auto-generated method stub
		return pricingRepository.findAllByRoom(r);
	}

	@Override
	public List<Pricing> findAll() {
		// TODO Auto-generated method stub
		return pricingRepository.findAll();
	}

	@Override
	public Pricing save(Pricing pricing) {
		// TODO Auto-generated method stub
		return pricingRepository.save(pricing);
	}

	@Override
	public Pricing findOneById(Long id) {
		// TODO Auto-generated method stub
		return pricingRepository.findOneById(id);
	}

	@Override
	public boolean deletePricing(Pricing pricing) {
		// TODO Auto-generated method stub
		try {
			pricingRepository.delete(pricing);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
}
