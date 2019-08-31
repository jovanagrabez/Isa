package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.repository.DestinationRepository;

public class DestinationServiceImpl  implements DestinationService{
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	  @Override
      public List<Destination> getAllDestinations() {
          return this.destinationRepository.findAll();
      }

      @Override
      public Destination getDestinationById(Long id) {
          return this.destinationRepository.findDestinationById(id);
      }

      @Override
      public Destination addDestination(Destination destination) {
          return this.destinationRepository.save(destination);
      }

      @Override
      public Destination updateDestination(Destination destination) {
          Destination savedDestination = this.destinationRepository.findDestinationById(destination.getId());
          savedDestination.setName(destination.getName());
          savedDestination.setCountry(destination.getCountry());


          return this.destinationRepository.save(savedDestination);
      }

      @Override
      public void deleteDestination(Destination destination) {
          this.destinationRepository.delete(destination);
      }
  

}
