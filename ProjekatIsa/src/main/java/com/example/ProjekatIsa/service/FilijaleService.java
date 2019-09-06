package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Filijale;

@Service
public interface FilijaleService {
	Filijale addFilijale(Filijale f);
	List<Filijale> getAll();
	Filijale findOneById(Long id);
}
