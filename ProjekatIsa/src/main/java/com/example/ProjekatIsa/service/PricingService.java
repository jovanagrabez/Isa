package com.example.ProjekatIsa.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.Room;

@Service
public interface PricingService {
	List<Pricing> findAllByRoom(Room r);
	List<Pricing> findAll();
}
