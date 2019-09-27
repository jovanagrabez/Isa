package com.example.ProjekatIsa.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;

@Service
public interface AdditionalServiceForHotelService {
	
	List<AdditionalServiceForHotel> getAll();
	List<AdditionalServiceForHotel> findAllByHotel(Hotel h);
	AdditionalServiceForHotel findOneById(Long id);
	AdditionalServiceForHotel addService(AdditionalServiceForHotel a);
	boolean deleteAdditionalServiceForHotel (AdditionalServiceForHotel a);
}
