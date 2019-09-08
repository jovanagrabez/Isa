package com.example.ProjekatIsa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FilterDTO;
import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.DTO.SearchDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.DestinationRepository;
import com.example.ProjekatIsa.repository.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired 
	private AviocompanyService avioService;
	
	@Autowired 
	private SeatService seatService;
	
	@Autowired 
	private SeatArrangementService seatAService;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	 @Override
	    public List<Flight> getAllFlights() {
	        return this.flightRepository.findAll();
	    }

	    @Override
	    public Flight getFlightById(Long id) {
	        return this.flightRepository.getOne(id);
	    }

	    @Override
	    public Flight addFlight(Flight flight) {
	    	for(Destination d : flight.getDestination()) {
	    	this.destinationRepository.save(d);
	    	}
	    	this.seatAService.saveSeatArrangement(flight.getSeatArrangement());
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight saveFlight(Flight flight) {
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight updateFlight(Flight flightDto) {

	    	for(Seat seat: flightDto.getSeats())
	    	    this.seatService.addSeat(seat);
	    	
	    	this.seatAService.addSeatArrangement(flightDto.getSeatArrangement());
	    	   this.flightRepository.save(flightDto);
	    	
	 /*       Aviocompany airline = this.avioService.getAirlineById(flightDto.getAirlineId());

	        Flight flight = this.flightRepository.getOne(flightDto.getId());

	        flight = new Flight(flightDto);
	        flight.setAirline(airline);

	        flight.setDestinations(new HashSet<>());
	        List<FlightDestination> flightDestinations = new ArrayList<>();
	        int flightChanges = 0;
	        for (Long destId : flightDto.getDestinations()) {
	            Destination destination = this.destinationService.getDestinationById(destId);          //pretvori dto u objekat
	            if (destination == null) {continue;}
	            FlightDestination flightDestination = new FlightDestination(flight, destination);

	            if (destId.equals(flightDto.getToDest())){
	                flightDestination.setDescription("arrival");
	            }else if (destId.equals(flightDto.getFromDest())){
	                flightDestination.setDescription("departure");
	            } else {
	                flightDestination.setDescription("connecting");
	                flightChanges++;
	            }
	            flightDestinations.add(flightDestination);
	         }
	        flight.setFlightChanges(flightChanges);
	        flight = this.flightRepository.save(flight);
	        this.flightDestinationService.deleteAndAddNewFlightDestinationsByFlight(flight.getId(), flightDestinations);     //brise stare veze

*/
	        return flightDto;
	    }

	    @Override
	    @Transactional
	    public boolean deleteFlight(Flight flight) {

	        boolean success;
	       try {
	           this.flightRepository.deleteById(flight.getId());
	           success = true;
	       } catch (Exception e) {
	           success = false;
	       }
	       return success;
	    }
	    
	    @Override
	    public Flight updateSeats(Flight flightDto) {
	    	
	    	
	    	 Flight flight = this.flightRepository.findOneById(flightDto.getId());
	         List<Seat> seats = new ArrayList<>();
	         
	         for (Seat seat : flightDto.getSeats()) {
	             if (seat.getSeatClass() != null) {
	                 if (seat.getSeatClass().equals("ECONOMY")) {            // postavi cenu ako je novo sediste, ili je klasa promenjena
	                     seat.setPrice(flight.getEconomyPrice());
	                 } else if (seat.getSeatClass().equals("PREMIUM_ECONOMY")) {
	                     seat.setPrice(flight.getPremiumEconomyPrice());
	                 } else if (seat.getSeatClass().equals("BUSINESS")) {
	                     seat.setPrice(flight.getBusinessPrice());
	                 } else if (seat.getSeatClass().equals("FIRST")) {
	                     seat.setPrice(flight.getFirstPrice());
	                 }
	             }
	        	 
	        
	        	 seat.setDiscountPrice(seat.getDiscountPrice());
	             seat = this.seatService.updateSeat(seat);                 // snimi promenjeno sediste
	             seats.add(seat);
	         }

	         for (Seat seat : seats) {
	             boolean contains = false;
	             for (Seat savedSeat : flight.getSeats()) {
	                 if (seat.getId().equals(savedSeat.getId())) {
	                     contains = true;
	                 }
	             }
	             if (!contains) {           
	            	 this.seatService.addSeat(seat);// ako je novo dodaj ga u flight
	                 flight.getSeats().add(seat);
	             }
	         }

	         flight = this.flightRepository.save(flight);
	         return flight;

	    }
	    
	    
	    @Override
	    public List<Flight> search(SearchDTO flightSearchDto) {

	        List<Flight> flights = new ArrayList<>();
	        ArrayList<Flight> flightsDestinations = new ArrayList<>();

	        List<Flight> sviletovi = new ArrayList<>();
	        sviletovi = this.flightRepository.findAll();
	        
	        for(int i=0; i < sviletovi.size();i++) {
	        	if(sviletovi.get(i).getTake_off().getDate() >= flightSearchDto.getFromDate().getDate()) {
	        		if(sviletovi.get(i).getLanding().getDate() <= flightSearchDto.getToDate().getDate()) {
	        		     flights.add(sviletovi.get(i));
	        		
	        		}
	        			
	        		}
	        }
	        
	        
	 /*       flights = this.flightRepository.findFlightsByTakeoffGreaterThanEqualAndLandingLessThanEqualAndTakeoffLessThanAndLandingGreaterThan(
                    flightSearchDto.getFromDate(), flightSearchDto.getToDate(),
                    flightSearchDto.getToDate(), flightSearchDto.getFromDate());*/
            flightsDestinations = searchFromAndToDestination(flightSearchDto, flights);


	

	        ArrayList<Flight> flightDtos = new ArrayList<>();

	        for (Flight flight : flightsDestinations){
	            flightDtos.add(flight);
	        }
	        return flightDtos;

	    }

	    private ArrayList<Flight> searchFromAndToDestination(SearchDTO flightSearchDto, List<Flight> flights){

	    	String[] stringsFrom = flightSearchDto.getFrom().split(" ");
	        String[] stringsTo = flightSearchDto.getTo().split(" ");

	        ArrayList<Flight> flightsDestinations = new ArrayList<>();
	        for (Flight flight : flights) {
	            boolean from = false;
	            boolean to = false;

	            Set<Destination> fdd = flight.getDestination();
	            for (Destination flightDestination : flight.getDestination()) {

	                if (flightDestination.getDescription().equals("from")){
	                    for (String fromString : stringsFrom) {                 //za svaku rec iz from trazi
	                        if ((flightDestination.getName().toLowerCase().contains(fromString.toLowerCase()) ||
	                                flightDestination.getCountry().toLowerCase().contains(fromString.toLowerCase()) ) ||
	                                !fromString.equals("")){

	                            from = true;
	                            break;
	                        }
	                    }
	                } else if (flightDestination.getDescription().equals("to")){

	                    for (String toString : stringsTo) {                 //za svaku rec iz from trazi
	                        if ((flightDestination.getName().toLowerCase().contains(toString.toLowerCase()) ||
	                                flightDestination.getCountry().toLowerCase().contains(toString.toLowerCase()))||
	                                !toString.equals("")){

	                            to = true;
	                            break;
	                        }
	                    }
	                }
	                if (from && to ) {

	                    int seatCount = 0;
	                    for (Seat seat : flight.getSeats()) {

	                        if (seat.getSeatClass().equals(flightSearchDto.getSeatClass()) &&       //ako je sedste odgovarajuce
	                             seat.getState().equals("free")){                                   //klase i slobodno
	                            seatCount++;
	                            if (seatCount >= flightSearchDto.getPersons()){break;}              //ako vec ima dovoljno,
	                                                                                                //ne trazi dalje
	                        }
	                    }

	                    if (seatCount >= flightSearchDto.getPersons()) {
	                        flightsDestinations.add(flight);                //ako odgovaraju i from i to destinacije
	                    }
	                    break;                                  //ne proveravaj dalje za taj flight jer vec odgovara
	                                                            //po destinacijama, ako ne odgovara po broju mesta,
	                                                            //nece odgovarati ni u sledecem prolazu
	                }
	            }
	        }

	        return flightsDestinations;
	    }
	    
	    
	    
	    @Override
	    public List<Flight> filter(FilterDTO flightFilter) {

	        List<Flight> oldFlights = flightFilter.getFlights();
	        List<Flight> newFlights = new ArrayList<>();


	        for (Flight flight : oldFlights) {
	            boolean isValid = true;
	          

	            if (flight.getEconomyPrice() < flightFilter.getFromPrice() &&               // ako ni jedna ne upada u min
	                    flight.getPremiumEconomyPrice() < flightFilter.getFromPrice() &&
	                    flight.getBusinessPrice() < flightFilter.getFromPrice() &&
	                    flight.getFirstPrice() < flightFilter.getFromPrice()) {
	                isValid = false;
	            }

	            if (flightFilter.getToPrice() != 0) {                                         // ako su svi veci od max
	                if (flight.getEconomyPrice() > flightFilter.getFromPrice() &&
	                        flight.getPremiumEconomyPrice() > flightFilter.getFromPrice() &&
	                        flight.getBusinessPrice() > flightFilter.getFromPrice() &&
	                        flight.getFirstPrice() > flightFilter.getFromPrice()) {
	                    isValid = false;
	                }
	            }

	            if (flight.getTime() < flightFilter.getFromDuration()) {
	                isValid = false;
	            }

	            if (flightFilter.getToDuration() != 0) {
	                if (flight.getTime() > flightFilter.getToDuration()) {
	                    isValid = false;
	                }
	            }


	            if (isValid) {
	                newFlights.add(flight);
	            }
	        }

	        return newFlights;
	    }
	    


}
