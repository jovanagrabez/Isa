package com.example.ProjekatIsa.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.User;

@Service
public interface UserService extends UserDetailsService {
	
	List<User> findAll();
	List<User> getAll();
	User save(User user);
	void sendVerificationMail(User user);
	User findUserByMail(String mail);
	User findByFirstName(String name);
	User findOneById(Long user_id);
	User update(User u);

	
	


}
