package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.service.AviocompanyService;
import com.example.ProjekatIsa.service.HotelService;

@RestController
@RequestMapping("/avioCompany")
public class AviocompanyController {
	
	
	@Autowired
	private AviocompanyService avioService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Aviocompany>  getAvioCompany() {
		
		System.out.println("Number of cinemas: " + avioService.getAll().size());
		
		return avioService.getAll();
}
	

}
