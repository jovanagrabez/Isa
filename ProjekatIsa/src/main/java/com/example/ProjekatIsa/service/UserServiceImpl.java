package com.example.ProjekatIsa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
   
   @Autowired
   private Environment env;
   
   @Autowired
   private JavaMailSender javaMailSender;
   
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
		
		System.out.println("Slanje emaila...");
		System.out.println("Saljemo mail na adresu:  " + user.getEmail());
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(user.getEmail());
		//spring.mail.username definisan u app.properties fajlu
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrda registracije");
		mail.setText("Pozdrav " + user.getFirstName() + ",\n Hvala vam sto koristite nas sajt."
				+ "Za potvrdu registracije posetite "+"http://localhost:4200/confirm-registration/"+user.getFirstName()+"\nVas travel.rs");
		
		javaMailSender.send(mail);
		System.out.println("Email poslat!");
	
		
	}



	@Override
	public User update( User new_user) {
		// TODO Auto-generated method stub
	
		
		

		return userRepository.save(new_user);
	}


	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findUserByMail(String mail) {
		// TODO Auto-generated method stub
		System.out.println("Usao u findUserbyMail");
		return userRepository.findOneByEmail(mail);
	}

	
	
	
private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		
		return getGrantedAuthorities(getPrivileges(roles));
	
	}
private List<String> getPrivileges(Collection<Role> roles) {
	  
    List<String> privileges = new ArrayList<>();
    List<MyRole> collection = new ArrayList<>();
    for (Role role : roles) {
        collection.addAll(role.getPrivileges());
    }
    for (MyRole item : collection) {
        privileges.add(item.getName());
    }
    return privileges;
}
private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
        authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
}


@Override
public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	 User user = userRepository.findOneByEmail(mail);
	 return user;
}


public void sendEmail(User user)throws MailException, InterruptedException {
	// TODO Auto-generated method stub
	
			System.out.println("Slanje emaila...");
			System.out.println("Saljemo mail na adresu:  " + user.getEmail());
			System.out.println("Saljemo mail sa adrese:  " + env.getProperty("spring.mail.username"));

			
			SimpleMailMessage mail = new SimpleMailMessage();
			
			mail.setTo(user.getEmail());
			//spring.mail.username definisan u app.properties fajlu
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("Potvrda registracije");
			mail.setText("Pozdrav " + user.getFirstName() + ",\n Hvala vam sto koristite nas sajt."
					+ "Za potvrdu registracije posetite "+"http://localhost:4200/confirm-registration/" + user.getId() + 
					"\n Uzivajte u odmoru. \n Vas DreamAir");
			
			javaMailSender.send(mail);
			System.out.println("Email poslat!");
}


@Override
public User findByFirstName(String name) {
	// TODO Auto-generated method stub
	return userRepository.findByFirstName(name);
}


@Override
public User findOneById(Long user_id) {
	// TODO Auto-generated method stub
	return userRepository.findOneById(user_id);
}


}
