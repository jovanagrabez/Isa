package com.example.ProjekatIsa.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.ProjekatIsa.model.Hotel;
@Service
public interface HotelService {

	List<Hotel> getAll();
	Hotel addHotel(Hotel h);
	boolean deleteHotel(Hotel h);
}
