package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
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
		
		System.out.println("Number of company: " + avioService.getAll().size());
		
		return avioService.getAll();
}
	
	
	@RequestMapping(
			value = "/getCompanyByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Aviocompany getCompanyByID(@PathVariable("id") Long id){
		
		return avioService.getCompanyByID(id);
		
	}
	
	 @PostMapping
	 public ResponseEntity<Aviocompany> addAirline(@RequestBody AviocompanyDTO airlineDTO){
//PREPRAVITI AKO SE BUDE ADRESA PRAVILA KAO NOVA KLASA ZBOG MAPA, DODATI ADRESU U POSEBNU TABELU PRVO PA ONDA VEZATI SA AVIOKOMPANIJOM
		 Aviocompany aviocompany = this.avioService.addAvioCompany(new Aviocompany(airlineDTO));
	        return ResponseEntity.ok(aviocompany);
	    }
	 
	 @PutMapping
	 public ResponseEntity<Aviocompany> updateAirline(@RequestBody AviocompanyDTO airlineDTO){
		  Aviocompany aviocompany = this.avioService.getCompanyByID(airlineDTO.getId());
	        if(aviocompany == null){
	            return ResponseEntity.notFound().build();
	        }
	        aviocompany = this.avioService.updateAviocompany(aviocompany);
	        return ResponseEntity.ok(aviocompany);
	 }
	 
	 @DeleteMapping(value = "/{id}")
	 
	    public ResponseEntity deleteAirline(@PathVariable Long id){
	        Aviocompany aviocompany = this.avioService.getCompanyByID(id);
	        if(aviocompany == null) {
	            return ResponseEntity.notFound().build();
	        }
	        boolean success = this.avioService.deleteAirline(aviocompany);

	        if (success) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	        }
	    }

}
