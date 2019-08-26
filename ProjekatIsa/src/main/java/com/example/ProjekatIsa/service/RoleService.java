package com.example.ProjekatIsa.service;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Authority;
import com.example.ProjekatIsa.model.Role;



@Service

public interface RoleService {
	//Role findByName(String name);

	Role findByName(Authority name);
	Role findByName(String name);
	Role findById(int id);

}

