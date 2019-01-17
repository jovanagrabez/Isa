package com.example.ProjekatIsa.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.User;

@Service

public interface UserService {
	
	Optional<User> login(User user);
	Optional<User> findByEmail(String email);
	List<User> findAll();
	List<User> getAll();
	User save(User user);
	void sendVerificationMail(User user);
	User getOne(Long id);
	User update(User old_user, User new_user);

}
