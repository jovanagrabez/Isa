package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.FlightDest;
import com.example.ProjekatIsa.repository.FlightDestRepository;

@Service
public class FlightDestServiceImpl implements FlightDestService{
	
	
	  @Autowired
	    private FlightDestRepository flightDestinationRepository;

	    @Override
	    public List<FlightDest> getAllFlightDestinations() {
	        return flightDestinationRepository.findAll();
	    }

	    @Override
	    public FlightDest getFlightDestinationById(Long id) {
	        return flightDestinationRepository.findFlightDestinationById(id);
	    }

	    @Override
	    public FlightDest addFlightDestination(FlightDest flightDestination) {
	        return flightDestinationRepository.save(flightDestination);
	    }

	    @Override
	    public void deleteFlightDestnation(FlightDest flightDestnation) {
	        flightDestinationRepository.deleteById(flightDestnation.getId());
	    }

	    @Override
	    public FlightDest updateFlightDestination(FlightDest flightDestination) {
	        FlightDest fd = flightDestinationRepository.findFlightDestinationById(flightDestination.getId());
	        fd.setDescription(flightDestination.getDescription());
	/*        fd.setDestination(flightDestination.getDestination());
	        fd.setFlight(flightDestination.getFlight());
*/
	        return flightDestinationRepository.save(fd);
	    }

	    @Override
	    public void deleteAndAddNewFlightDestinationsByFlight(Long flightId, List<FlightDest> flightDestinations) {

	   /*     List<FlightDest> flightDestinationsSaved = this.flightDestinationRepository.findAll();

	        for (FlightDest savedFD : flightDestinationsSaved) {

	   /*         if (savedFD.getFlight().getId().equals(flightId)) {
	                boolean isValid = false;
	                for (FlightDest newFD : flightDestinations) {
	     /*               if (savedFD.getDestination().getId().equals(newFD.getDestination().getId())) {       //ako se vec nalazi u bazi samo je description drugi
	                        isValid = true;     //nalazi se u novim (ne brisi)
	                        if (!savedFD.getDescription().equals(newFD.getDescription())) {
	                            savedFD.setDescription(newFD.getDescription());
	                            savedFD = this.flightDestinationRepository.save(savedFD);               //update-uje ga na novu vr
	                        }
	                    }
	                } if (!isValid) {                                                                   //ako se ne nalazi u novim
	                    this.flightDestinationRepository.deleteById(savedFD.getId());
	                }
	            }
	        }
	        flightDestinationsSaved = this.flightDestinationRepository.findAll();
	        for (FlightDest newFD : flightDestinations) {
	            boolean isSaved = false;
	            for (FlightDest savedFD : flightDestinationsSaved) {                               //ako je nova upisi je
	                if (savedFD.getFlight().getId().equals(flightId)
	                        && savedFD.getDestination().getId().equals(newFD.getDestination().getId())
	                        && savedFD.getDescription().equals(newFD.getDescription())) {

	                    isSaved = true;
	                    break;
	                }
	            }
	            if (!isSaved){
	                this.flightDestinationRepository.save(newFD);
	            }
	        }*/

	    }

}
