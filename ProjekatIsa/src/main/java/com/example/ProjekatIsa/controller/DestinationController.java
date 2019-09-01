package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.service.AviocompanyService;
import com.example.ProjekatIsa.service.DestinationService;

@Controller
@RequestMapping(value="/destination",produces = MediaType.APPLICATION_JSON_VALUE)
public class DestinationController {
	
	@Autowired
	private DestinationService destinationService;
	 
	
	@Autowired
	private AviocompanyService avioService;
	
	@Autowired
	private AviocompanyRepository repository;
	
	 @PostMapping
	 public ResponseEntity<Destination> addADestination(@RequestBody Destination destinationDTO){
				 this.destinationService.addDestination(destinationDTO);
			        return ResponseEntity.ok(destinationDTO);
			    }
			 
	 @GetMapping
	 public List<Destination>  getDestinations() {
			
			return destinationService.getAllDestinations();
	 }
	 
	 
	 @DeleteMapping(value = "/{id}/{avio_id}")
	 
	    public ResponseEntity deleteDestination(@PathVariable("id") Long id,@PathVariable("avio_id") Long avio_id){
	        Destination destination = this.destinationService.getDestinationById(id);
	        Aviocompany a = this.avioService.getCompanyByID(avio_id);
	        a.getDestination().remove(destination);
	        this.repository.save(a);
	        if(destination == null) {
	            return ResponseEntity.notFound().build();
	        }
	        this.destinationService.deleteDestination(destination);

	            return ResponseEntity.ok().build();
	      
	    }

}
