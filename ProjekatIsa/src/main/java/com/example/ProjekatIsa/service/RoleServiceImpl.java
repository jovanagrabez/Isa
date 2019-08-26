package com.example.ProjekatIsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Authority;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.repository.RoleRepository;



@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public Role findByName(Authority name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}


	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Role findById(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}
	
}
