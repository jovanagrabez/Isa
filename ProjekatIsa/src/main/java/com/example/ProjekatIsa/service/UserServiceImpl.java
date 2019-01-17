package com.example.ProjekatIsa.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserCreateForm;
import com.example.ProjekatIsa.repository.RoleRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service


public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
   
   @Autowired
	private UserRepository userRepository;
   
   @Override
	public Optional<User> findByEmail(String email) throws AccessDeniedException  {
		return userRepository.findByEmail(email);
	}
	
	
   @Override
	public Optional<User> login(User user) {
		Optional<User> _user = userRepository.findByEmail(user.getEmail());
		
		if(_user != null) {
			if(_user.get().getPasswordHash().equals(user.getPasswordHash())) {
				return _user;
			} else {
				return null;
			}
		}
		return null;
	}
   
   @Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public void sendVerificationMail(User user) {
		// TODO Auto-generated method stub
	
		
	}


	@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User update(User old_user, User new_user) {
		// TODO Auto-generated method stub
		if( new_user.getFirstName() != null){
			old_user.setFirstName(new_user.getFirstName());
		}
		if(new_user.getPhoneNumber() != null){
			old_user.setPhoneNumber(new_user.getPhoneNumber());
		}
		if(new_user.getLastName() != null){
			old_user.setLastName(new_user.getLastName());
		}
		if(new_user.getCity() != null){
			old_user.setCity(new_user.getCity());
		}
		
		

		return userRepository.save(old_user);
	}


	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
