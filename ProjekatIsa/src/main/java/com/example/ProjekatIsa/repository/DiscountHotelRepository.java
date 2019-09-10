package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.DiscountHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;

@Repository
public interface DiscountHotelRepository extends JpaRepository<DiscountHotel,Long>{
	DiscountHotel findOneById(Long id);
	List<DiscountHotel> findAllByHotel(Hotel h);
	DiscountHotel findOneByRoom(Room room);
}
