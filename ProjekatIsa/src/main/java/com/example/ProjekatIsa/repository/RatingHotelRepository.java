package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.User;


public interface RatingHotelRepository  extends JpaRepository<RatingHotel, Long>{

	List<RatingHotel> findAllByUser(User user);
	List<RatingHotel> findAllByHotel(Hotel hotel);

}
