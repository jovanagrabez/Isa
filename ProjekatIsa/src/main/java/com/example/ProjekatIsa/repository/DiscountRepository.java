package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

	Discount findOneById(Long id);

}
