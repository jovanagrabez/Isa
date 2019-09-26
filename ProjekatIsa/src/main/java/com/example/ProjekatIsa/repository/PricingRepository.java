package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.Room;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long>{
	List<Pricing> findAll();
	List<Pricing> findAllByRoom(Room r);
	Pricing save(Pricing pricing);
	Pricing findOneById(Long id);
}
