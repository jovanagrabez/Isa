package com.example.ProjekatIsa.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserCreateForm;
import com.example.ProjekatIsa.model.VerificationToken;
import com.example.ProjekatIsa.repository.RoleRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;
@Service


public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	 public static final String TOKEN_INVALID = "invalidToken";
	 public static final String TOKEN_EXPIRED = "expired";
   public static final String TOKEN_VALID = "valid";
   
   @Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private VerificationTokenRepository tokenRepository;

	@Override
	public Optional<User> getUserById(long id) {
		// TODO Auto-generated method stub
		LOGGER.debug("Getting user={}", id);
		return Optional.ofNullable(userRepository.findOneById(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		LOGGER.debug("Getting user by email={}", email);
		return userRepository.findOneByEmail(email);
	}

	@Override
	public User getOneById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOneById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		LOGGER.debug("Getting all users");
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public boolean updateUserRole(User u) {
		// TODO Auto-generated method stub
		User user = userRepository.findOneById(u.getId());
		user.getRoles().clear();
		for(MyRole role:u.getRoles()) {
			user.getRoles().add(role);
		}
		user.setId(u.getId());
		
		//userRepository.save(user);
		userRepository.flush();
		return true;
	}

	@Override
	public boolean updateUserInfo(User u) {
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Set<User> getUsersByIdIn(Set<Long> ids) {
		// TODO Auto-generated method stub
		return userRepository.findByIdIn(ids);
	}

	@Override
	public User create(UserCreateForm form) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setFirstName(form.getName());
		user.setLastName(form.getLastName());
		user.setCity(form.getCity());
		user.setPhoneNumber(form.getPhoneNumber());
		MyRole role=roleRepository.findOneByName(Role.USER.toString());
		System.out.println("rola je:"+role.getName());
		user.getRoles().add(role);

		System.out.println("dodao role");
		userRepository.save(user);
		return user;
	}

	@Override
	public void createVerificationTokenForUser(final User user, final String token) {
		// TODO Auto-generated method stub
		final VerificationToken myToken = new VerificationToken(token, user);
       tokenRepository.save(myToken);		
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		 final VerificationToken verificationToken = tokenRepository.findByToken(token);
	        if (verificationToken == null) {
	            return TOKEN_INVALID;
	        }
	        final User user = verificationToken.getUser();
	        final Calendar cal = Calendar.getInstance();
	        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	            tokenRepository.delete(verificationToken);
	            return TOKEN_EXPIRED;
	        }

	       // user.setEnabled(true);
	        // tokenRepository.delete(verificationToken);
	        userRepository.save(user);
	        return TOKEN_VALID;
	}

	@Override
	public User getUserByToken(final String verificationToken) {
		// TODO Auto-generated method stub
		 final VerificationToken token = tokenRepository.findByToken(verificationToken);
	        if (token != null) {
	            return token.getUser();
	        }
	        return null;
	}

	@Override
	public void saveRegisteredUser(final User user) {
       userRepository.save(user);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return tokenRepository.findByToken(VerificationToken);
	}

}
