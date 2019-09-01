package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Destination;

@Service
public interface DestinationService {
	
	    List<Destination> getAllDestinations();
	    Destination getDestinationById(Long id);
	    Destination addDestination(Destination destination);
	    Destination updateDestination(Destination destination);
	    void deleteDestination(Destination destination);

}
