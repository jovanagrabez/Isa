package com.example.ProjekatIsa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<MyRole, Long> {
	MyRole findOneByName(Role role);

	MyRole findByName(Role role);
}
