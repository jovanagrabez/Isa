package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.SystemDiscountDTO;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SystemDiscount;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.SystemDiscountRepository;
import com.example.ProjekatIsa.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/discounts")
public class DiscountController {

	@Autowired
	private SystemDiscountRepository sdRep;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(
			value = "/getDiscount/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDiscount(@PathVariable("id") Long id) {
		System.out.println("Dosao u getDiscount");
		SystemDiscount returnValue = sdRep.findOneById(id);
		if (returnValue!=null) {
	        return new ResponseEntity<SystemDiscount>(returnValue,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/changeDiscount/{id}", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changeDiscount(@PathVariable("id") Long id,@RequestBody SystemDiscountDTO sd) {
		System.out.println("Dosao u changeDiscount");
		SystemDiscount newSD = sdRep.findOneById(id);
		if (newSD!=null) {
			newSD.setAmount(sd.getAmount());
			newSD.setPercent(sd.getPercent());
			sdRep.save(newSD);
	        return new ResponseEntity<SystemDiscount>(newSD,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/usePoints/{id}/{points}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> usePoints(@PathVariable("id") Long id,@PathVariable("points") Long points) {
		System.out.println("Dosao u usePoints");

		User user = userService.findOneById(id);
		if(user!=null) {
			System.out.println("nasao usera");
			Double newPoints = user.getPoints()-points;
			System.out.println("novi poeni "+newPoints);
			user.setPoints(newPoints);
			userService.save(user);
			return new ResponseEntity<>(HttpStatus.OK);

		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(
			value = "/addPoints/{id}/{points}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addPoints(@PathVariable("id") Long id,@PathVariable("points") Long points) {
		System.out.println("Dosao u usePoints");

		User user = userService.findOneById(id);
		if(user!=null) {
			System.out.println("nasao usera");
			Double newPoints = user.getPoints()+points;
			System.out.println("novi poeni "+newPoints);
			user.setPoints(newPoints);
			userService.save(user);
			return new ResponseEntity<>(HttpStatus.OK);

		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

		}
	}
}
