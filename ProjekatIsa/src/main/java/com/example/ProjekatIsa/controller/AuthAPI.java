package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.config.JwtProvider;
import com.example.ProjekatIsa.config.JwtResponse;
import com.example.ProjekatIsa.config.LoginForm;
import com.example.ProjekatIsa.config.RegisterForm;
import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.RoleRepository;
import com.example.ProjekatIsa.repository.UserRepository;



//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
 
    	System.out.println(loginRequest.getEmail() + ":" + loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = jwtProvider.generateJwtToken(authentication);
		System.out.println("JWT TOKEN: " + jwt);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	}

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterForm signUpRequest) {
        

        // Creating user's account
        User user = new User();
        
        user.setEmail(signUpRequest.getEmail());
        user.setPasswordHash(encoder.encode(signUpRequest.getPasswordHash()));
        user.setFirstName(signUpRequest.getFirsName());
        user.setLastName(signUpRequest.getLastname());
        user.setCity(signUpRequest.getCity());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        
        
       
        userRepository.save(user);

        
        
      

        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
    }

}
