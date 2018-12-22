package com.example.ProjekatIsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.MyRole;

public interface RoleRepository extends JpaRepository<MyRole, Long> {
	MyRole findOneByName(String role);
}
