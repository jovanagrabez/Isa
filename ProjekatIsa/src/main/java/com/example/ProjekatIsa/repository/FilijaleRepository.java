package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Filijale;

@Repository
public interface FilijaleRepository extends JpaRepository<Filijale,Long> {

}
