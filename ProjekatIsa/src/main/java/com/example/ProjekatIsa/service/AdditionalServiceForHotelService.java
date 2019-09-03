package com.example.ProjekatIsa.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;

@Service
public interface AdditionalServiceForHotelService {
	
	List<AdditionalServiceForHotel> getAll();
	AdditionalServiceForHotel addService(AdditionalServiceForHotel a);
}
