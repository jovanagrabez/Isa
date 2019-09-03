package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;

public interface AdditionalServiceForHotelRepository extends JpaRepository<AdditionalServiceForHotel,Long>{
	List<AdditionalServiceForHotel> findAll();
	List<AdditionalServiceForHotel> findAllByHotel(Hotel h);
	AdditionalServiceForHotel save(AdditionalServiceForHotel a);
}
