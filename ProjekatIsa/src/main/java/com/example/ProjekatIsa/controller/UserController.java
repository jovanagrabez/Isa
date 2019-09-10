package com.example.ProjekatIsa.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserTokenState;
import com.example.ProjekatIsa.security.TokenUtils;
import com.example.ProjekatIsa.security.auth.JwtAuthenticationRequest;
import com.example.ProjekatIsa.service.RoleService;
import com.example.ProjekatIsa.service.UserService;
import com.example.ProjekatIsa.service.UserServiceImpl;

import javax.ws.rs.core.Context;
import org.owasp.encoder.Encode;
import org.springframework.mobile.device.Device;




@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")

public class UserController {	 
	 
	 @Autowired
	 TokenUtils tokenUtils;
	 
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Autowired
	 private AuthenticationManager manager;
    
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private UserServiceImpl service; 
	 
	 @Autowired
	 private RoleService roleService;
	   
	// @Autowired
	//PasswordEncoder encoder;
	 
	 @PutMapping
	 public ResponseEntity<User> updateUser(@RequestBody User u){
		 this.userService.update(u);
		 return ResponseEntity.ok(u);
	 }
	 @RequestMapping(value ="/registerUser",
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity<?> registerUser(@Valid @RequestBody User user1, BindingResult result){
			System.out.println("-----Registracija kotisnika-----");
			User oldUser = userService.findUserByMail(Encode.forHtml(user1.getEmail()));
			System.out.println(user1.firstName + "firstName" + user1.getLastName());
			
			
			if(result.hasErrors()) {
				//404
				System.out.println("ERROR registration");
				return new ResponseEntity<>(new UserTokenState("error",(long)0), HttpStatus.NOT_FOUND);
			}
			if(!checkMail(user1.getEmail())) {
				System.out.println("ERROR registration");
				return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
			}
			if(!checkCharacters(user1.getFirstName())) {
				System.out.println("ERROR registration");
				return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
			}
			if(!checkCharacters(user1.getLastName())) {
				System.out.println("ERROR registration");
				return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
			}
			
			if(oldUser == null) {
				User newUser = new User();
				String newPassword = user1.getPassword();
				System.out.println("Novi korisnik" + user1.firstName + user1.getPassword());
				if(newPassword.equals("") || newPassword == null) {
					return null;
				}
				
				String hash = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
				
				System.out.println("------Hesiranje lozinke------");
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedP = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newPassword, hash);
				newUser.setEmail(user1.getEmail());
				newUser.setFirstName(user1.getFirstName());
				newUser.setLastName(user1.getLastName());
				newUser.setPassword(hashedP);
				newUser.setCity(user1.getCity());
				newUser.setPhoneNumber(user1.getPhoneNumber());	
				newUser.setPoints(0.0);
				newUser.setFirstLogin(true);
												
				Role rolica = new Role();
				rolica = roleService.findById(1);
				System.out.println("Uloga id" + rolica.getId());

				Collection<Role> r1 = new ArrayList<Role>();
				r1.add(rolica);
				newUser.setRoles(r1);
				newUser.setEnabled(true);
				newUser.setVerified(false);

				System.out.println("Uloga registrovanog korisnika je:" + newUser.getRoles().toString());
				userService.save(newUser);
				System.out.println("Uloga sacuvanog korisnika" + newUser.getRoles());
				System.out.println("New user registration: USPJESNO" + newUser);
				
				try{
					service.sendEmail(newUser);
				}catch( Exception e )
				{
					System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
				}
				
				return new ResponseEntity<>(newUser, HttpStatus.CREATED);
				
				
			} else {
				System.out.println("Postoji korisnik sa istom email adresom ");
				user1.setEmail("error");
				System.out.println("New user reg: Email is already in use");
				return new ResponseEntity<>(user1, HttpStatus.NOT_FOUND);
			}
			
		}
	 
	 @RequestMapping(value = "/confirmMail/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> verifikujKorisnika(@PathVariable("id") Long name){
						
			User user = new User();
		    user = userService.findOneById(name);
		    System.out.println(name);
			System.out.println(name);
			user.setVerified(true);
			userService.save(user);
			
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}
	    
	    

		@RequestMapping(value="/all", method = RequestMethod.GET)
			public List<User>  getUsers() {
				
				System.out.println("Number of hotels: " + userService.getAll().size());
				
				return userService.getAll();
		}
		
		
		
		private byte[] hashPassword(String password, byte[] salt) {
			int iterations = 10000;
			int keyLength = 512;
			char[] passwordChars = password.toCharArray();
			
			try {
				SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
				PBEKeySpec spec = new PBEKeySpec( passwordChars, salt, iterations, keyLength );
				SecretKey key;
				
				try {
					key = secretKey.generateSecret( spec );
					byte[] dataHash = key.getEncoded( );
			        return dataHash;
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}catch (NoSuchAlgorithmException e) {
				  throw new RuntimeException( e );
			}
			return null;
		
		}
		
		
		
  public boolean checkCharacters(String data) {
			if(data.isEmpty()) {
				return false;
			}
			for(Character c :data.toCharArray()) {
				if(Character.isWhitespace(c)== false && Character.isLetterOrDigit(c) == false) {
					return false;
				}
			}
			
			return true;
		}
  public boolean checkId(String id) {
			if(id.isEmpty()) {
				return false;
			}
			for(Character c :id.toCharArray()) {
				if(!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
  public boolean checkMail(String mail) {
			if(mail.isEmpty()) {
				return false;
			}
			if(mail.contains(";")) {
				return false;
			}
			
			if(mail.contains(",")) {
				return false;
			}
			for(Character c:mail.toCharArray()) {
				if(Character.isWhitespace(c)) {
					return false;
				
				}
					
			}
			return true;
		}
		
	    
  @RequestMapping(value ="/registerAdmin/{num}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody User user1,@PathVariable("num") Long num, BindingResult result){
		System.out.println("-----Registracija kotisnika-----");
		User oldUser = userService.findUserByMail(Encode.forHtml(user1.getEmail()));
		System.out.println(user1.firstName + "firstName" + user1.getLastName());
		
		
		if(result.hasErrors()) {
			//404
			System.out.println("ERROR registration");
			return new ResponseEntity<>(new UserTokenState("error",(long)0), HttpStatus.NOT_FOUND);
		}
		if(!checkMail(user1.getEmail())) {
			System.out.println("ERROR registration");
			return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getFirstName())) {
			System.out.println("ERROR registration");
			return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
		}
		if(!checkCharacters(user1.getLastName())) {
			System.out.println("ERROR registration");
			return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
		}
		
		if(oldUser == null) {
			User newUser = new User();
			String newPassword = user1.getPassword();
			System.out.println("Novi korisnik" + user1.firstName + user1.getPassword());
			if(newPassword.equals("") || newPassword == null) {
				return null;
			}
			
			String hash = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
			
			System.out.println("------Hesiranje lozinke------");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedP = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newPassword, hash);
			newUser.setEmail(user1.getEmail());
			newUser.setFirstName(user1.getFirstName());
			newUser.setLastName(user1.getLastName());
			newUser.setPassword(hashedP);
			newUser.setCity(user1.getCity());
			newUser.setPhoneNumber(user1.getPhoneNumber());	
			newUser.setPoints(0.0);
			//ako je num 1 - uloga je avio
			//ako je num 2 - uloga je hotel
			//ako je num 3 - uloga je car

			Role rolica = new Role();
			
			if(num == 1) {
				rolica = roleService.findById(3);

			}else if(num == 2) {
				rolica = roleService.findById(4);
			}else if(num == 3) {
				rolica = roleService.findById(5);
			}
			//rolica = roleService.findById(1);
			System.out.println("Uloga id" + rolica.getId());

			Collection<Role> r1 = new ArrayList<Role>();
			r1.add(rolica);
			newUser.setRoles(r1);
			newUser.setEnabled(true);
			newUser.setVerified(true);
			newUser.setFirstLogin(false);
			System.out.println("Uloga registrovanog korisnika je:" + newUser.getRoles().toString());
			userService.save(newUser);
			System.out.println("Uloga sacuvanog korisnika" + newUser.getRoles());
			System.out.println("New user registration: USPJESNO" + newUser);
			
			try{
				service.sendEmail(newUser);
			}catch( Exception e )
			{
				System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			}
			
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
			
			
		} else {
			System.out.println("Postoji korisnik sa istom email adresom ");
			user1.setEmail("error");
			System.out.println("New user reg: Email is already in use");
			return new ResponseEntity<>(user1, HttpStatus.NOT_FOUND);
		}
		
	} 
	
  @RequestMapping(value ="/changePassword/{id}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> changePassword(@Valid @RequestBody User user1,@PathVariable("id") Long idUser, BindingResult result){
	  
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	  User newUser = userService.findOneById(idUser);
	  System.out.println(encoder.encode(user1.getPassword())+ " - user s fronta...pronadjen user: " + newUser.getPassword());
	  
	  if (user1.getPassword()!=null) {
		  if (encoder.matches((user1.getPassword()), newUser.getPassword())) {
			  if (user1.getPasswordConfirm().length() < 6) {
				  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			  }
			  //newUser.setVerified(true);
			  newUser.setFirstLogin(true);
			  
			  String hash = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
			  String hashedP = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPasswordConfirm(), hash);
				
			  newUser.setPassword(hashedP);
			  userService.save(newUser);
			  return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		  }
		  else {
			  System.out.println("Ne poklapaju se trenutna lozinka i unijeta trenutna lozinka");
			  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
	   
}    
