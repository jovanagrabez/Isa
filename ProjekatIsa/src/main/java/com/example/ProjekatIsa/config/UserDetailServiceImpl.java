package com.example.ProjekatIsa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
    UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        User user = userRepository.findByEmail(username)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );
        //MILAN: izbacio sam vasu implementaciju UserDetails-a i prilagodio klasi User
        return user;
    }

}
