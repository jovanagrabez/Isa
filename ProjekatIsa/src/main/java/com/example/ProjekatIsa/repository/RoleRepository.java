package com.example.ProjekatIsa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Authority;
import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(Authority name);
	Role findById(int id);
}
