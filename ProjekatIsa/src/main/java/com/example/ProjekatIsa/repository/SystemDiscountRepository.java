package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.SystemDiscount;

@Repository
public interface SystemDiscountRepository extends JpaRepository<SystemDiscount, Long> {
	SystemDiscount findOneById(Long id);
	SystemDiscount save(SystemDiscount systemDiscount);
}
