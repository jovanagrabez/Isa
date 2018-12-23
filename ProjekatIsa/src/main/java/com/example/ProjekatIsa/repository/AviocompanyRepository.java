package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.Aviocompany;


public interface AviocompanyRepository extends JpaRepository<Aviocompany, Long> {

	Aviocompany findOneById(Long id);
}
