package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.FilterDTO;
import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.DTO.ReservationDto;
import com.example.ProjekatIsa.DTO.SearchDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.Reservation;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.service.AviocompanyService;
import com.example.ProjekatIsa.service.FlightReservationService;
import com.example.ProjekatIsa.service.FlightService;
import com.example.ProjekatIsa.service.ReservationService;

@RestController
@RequestMapping(value="/flight",produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightControler {

	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private AviocompanyService avioService;
	
	@Autowired
	private AviocompanyRepository repository;
	
	@Autowired
	private FlightReservationService reservationsService;
	
	
	@Autowired
	private ReservationService resServiceComplete;
	
	
	@GetMapping(value="/{id}")
	public Flight getFlightByID(@PathVariable("id") Long id){
		
		return flightService.getFlightById(id);
	}
	
	@PostMapping
	 public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
		
				 this.flightService.addFlight(flight);
			        return ResponseEntity.ok(flight);
			    }
	
	
	@PostMapping(value="/reservations")
   
    public ResponseEntity<FlightReservation> reserve(@RequestBody FlightReservation reservationDto) {
         this.reservationsService.createFlightReservation(reservationDto);

        return ResponseEntity.ok(reservationDto);

    }
	
	
	
	 @PostMapping(value="/resComplete")
	    public ResponseEntity<ReservationDto> completeReservation(@RequestBody ReservationDto reservationDto) {
	        Reservation reservation = resServiceComplete.createReservation(reservationDto);

	        reservationDto = new ReservationDto(reservation);
	        return ResponseEntity.ok(reservationDto);

	    }
	
	 @GetMapping(value = "/email/{id}")
	    public void sendReservationInfoMailAfterCreating(@PathVariable Long id) {
	        try {
	            this.resServiceComplete.sendReservationEmail(id);
	        } catch (Exception e) {}
	    }
	 
	 
	 
	 @GetMapping(value = "/invite/{id}")
	    public ResponseEntity<List<Passenger>> getPassengerSeatReservation(@PathVariable Long id) {

	        Passenger pass = this.reservationsService.getPassengerSeatById(id);
	        if (pass == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return new ResponseEntity(pass, HttpStatus.OK);
	    }

	    @DeleteMapping(value = "/invite/{id}")
	    public ResponseEntity deleteInvitation(@PathVariable Long id) {
	        this.reservationsService.deleteInvite(id);

	        return ResponseEntity.ok().build();
	    }
	 
	
	@DeleteMapping(value = "/{id}/{avio_id}")
	 
    public ResponseEntity deleteFlight(@PathVariable("id") Long id,@PathVariable("avio_id") Long avio_id){
        Flight flight = this.flightService.getFlightById(id);
        Aviocompany a = this.avioService.getCompanyByID(avio_id);
        a.getFlight().remove(flight);
        this.repository.save(a);
        if(flight == null) {
            return ResponseEntity.notFound().build();
        }
        this.flightService.deleteFlight(flight);

            return ResponseEntity.ok().build();
      
    }
	
	
	 @PutMapping(value="/update")
	 public ResponseEntity<Flight> updateAirline(@RequestBody Flight flight){
		    if(flight == null){
	            return ResponseEntity.notFound().build();
	        }
	        this.flightService.updateFlight(flight);
	        return ResponseEntity.ok(flight);
	 }
	 
	 @PutMapping(value = "/seats")
	 
	    public ResponseEntity<Flight> updateFlightSeats(@RequestBody Flight flightDto){

	        Flight flight = this.flightService.getFlightById(flightDto.getId());
	        if (flight == null){
	            return ResponseEntity.notFound().build();
	        }

	        try {
	            flight = this.flightService.updateSeats(flightDto);
	            return new ResponseEntity(flight, HttpStatus.OK);
	        }catch (Exception e) {

	        }
	        return new ResponseEntity(flight, HttpStatus.FORBIDDEN);
	    }
	 
	 @PostMapping(value = "/search")
	    public ResponseEntity<Set<Flight>> search(@RequestBody SearchDTO flightSearchDto) {

	        Set<Flight> flightDtos = new HashSet<>();

	        flightDtos.addAll(this.flightService.search(flightSearchDto));
	        return new ResponseEntity(flightDtos, HttpStatus.OK);

	    }

	    @PostMapping(value = "/filter")
	    public ResponseEntity<List<Flight>> filter(@RequestBody FilterDTO flightFilterDto) {

	        List<Flight> flightDtos = new ArrayList<>();

	        flightDtos = this.flightService.filter(flightFilterDto);
	        return new ResponseEntity(flightDtos, HttpStatus.OK);

	    }
}
