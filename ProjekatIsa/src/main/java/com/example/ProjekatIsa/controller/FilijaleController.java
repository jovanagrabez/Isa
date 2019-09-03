package com.example.ProjekatIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.service.FilijaleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/filijale")
public class FilijaleController {
	
	@Autowired
	FilijaleService filService;
	
	@Autowired
	FilijaleRepository filRepository;
	
	@RequestMapping(value="/addFilijale",method = RequestMethod.POST)
	public ResponseEntity<?> addNewFil(@RequestBody Filijale fil){
		System.out.println("dosaooo u addFil");
		Filijale nova = new Filijale();
		nova.setAdresa(fil.getAdresa());
		nova.setDrzava(fil.getDrzava());
		nova.setGrad(fil.getGrad());
		
		filRepository.save(nova);
		return new ResponseEntity<>(nova,HttpStatus.OK);
		
	}

}
