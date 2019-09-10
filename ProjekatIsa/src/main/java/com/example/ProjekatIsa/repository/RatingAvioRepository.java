package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.RatingAvio;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.User;


@Repository
public interface RatingAvioRepository extends JpaRepository<RatingAvio, Long> {

	List<RatingAvio> findAllByUser(User user);

	List<RatingAvio> findAllByAviocompany(Aviocompany hotel);


}
