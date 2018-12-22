package com.example.ProjekatIsa.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserCreateForm;
import com.example.ProjekatIsa.model.VerificationToken;
@Service

public interface UserService {
	
	Optional<User> getUserById(long id);

	Optional<User> getUserByEmail(String email);

	User getOneById(Long id);
	
	Collection<User> getAllUsers();

	boolean updateUserRole(User u);
	
	boolean updateUserInfo(User u);

	List<User> getAll();
	
	Set<User> getUsersByIdIn(Set<Long> ids);
	
	User create(UserCreateForm form);
	
	void createVerificationTokenForUser(final User user, final String token);
	
	String validateVerificationToken(String token);
	
    public User getUserByToken(final String verificationToken);
    
    void saveRegisteredUser(User user);
    
    public VerificationToken getVerificationToken(final String VerificationToken);


}
