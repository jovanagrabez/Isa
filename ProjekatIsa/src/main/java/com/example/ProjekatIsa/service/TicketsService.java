package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ProjekatIsa.model.Tickets;
import com.example.ProjekatIsa.repository.TicketsRepository;

public class TicketsService {

	@Autowired
	private TicketsRepository ticketRepository;
	
	public Page<Tickets> findAll(Pageable page) {
		return ticketRepository.findAll(page);
	}

}
