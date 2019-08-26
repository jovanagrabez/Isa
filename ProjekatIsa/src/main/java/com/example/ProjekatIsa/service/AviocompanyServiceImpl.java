package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.HotelRepository;

@Service
public class AviocompanyServiceImpl  implements AviocompanyService{
	
	@Autowired
	private AviocompanyRepository avioRepository;
	
	@Override
	public List<Aviocompany> getAll() {
		return avioRepository.findAll();
	}

	
	@Override
	public Aviocompany getCompanyByID(Long id) {
		
		return avioRepository.findOneById(id);
		
	}
	
	@Override
	public Aviocompany addAvioCompany(Aviocompany avioCompany) {
		return this.avioRepository.save(avioCompany);
	}
	
	@Override
    public boolean deleteAirline(Aviocompany aviocompany) {

        try {
            this.avioRepository.delete(aviocompany);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
