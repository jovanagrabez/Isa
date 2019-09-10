package com.example.ProjekatIsa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
import com.example.ProjekatIsa.DTO.DestinationDTO;
import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.DTO.RentACarDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.RatingAvio;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.FlightRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.RatingAvioRepository;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.service.AviocompanyService;
import com.example.ProjekatIsa.service.DestinationService;
import com.example.ProjekatIsa.service.HotelService;


@RestController
@RequestMapping(value="/avioCompany",produces = MediaType.APPLICATION_JSON_VALUE)

public class AviocompanyController {
	
	
	@Autowired
	private AviocompanyService avioService;
	
	@Autowired
	private DestinationService destinationService;
	
	
	@Autowired
	private AviocompanyRepository avioRepository;
	
	@Autowired
	private RatingAvioRepository ratingAvioRepository;
	
	
	@Autowired
	private FlightReservationRepository flightReservationRep;
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Aviocompany>  getAvioCompany() {
		
		System.out.println("Number of company: " + avioService.getAll().size());
		
		return avioService.getAll();
}
	
	
	@GetMapping(value="/{id}")
	public Aviocompany getCompanytByID(@PathVariable("id") Long id){
		
		return avioService.getCompanyByID(id);
	}
	
	
	@GetMapping(value="flight/{id}")
	public Aviocompany getCompany(@PathVariable("id") Long id){
		
		return avioService.getCompanyByFlightId(id);
	}
	
	
	 @PostMapping
	 public ResponseEntity<Aviocompany> addAirline(@RequestBody AviocompanyDTO airlineDTO){
//PREPRAVITI AKO SE BUDE ADRESA PRAVILA KAO NOVA KLASA ZBOG MAPA, DODATI ADRESU U POSEBNU TABELU PRVO PA ONDA VEZATI SA AVIOKOMPANIJOM
		 Aviocompany aviocompany = this.avioService.addAvioCompany(new Aviocompany(airlineDTO));
	        return ResponseEntity.ok(aviocompany);
	    }
	 
	 @PutMapping(value="/update")
	 public ResponseEntity<Aviocompany> updateAirline(@RequestBody Aviocompany airlineDTO){
		    if(airlineDTO == null){
	            return ResponseEntity.notFound().build();
	        }
	        this.avioService.updateAviocompany(airlineDTO);
	        return ResponseEntity.ok(airlineDTO);
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
	 
	 
	 @RequestMapping(value="/sortForm/{param}",
				method = RequestMethod.POST)
		public ResponseEntity<?> sortForm(@PathVariable("param") String param, @RequestBody List<AviocompanyDTO> servisi){
			System.out.println("usao u sortiranjee");
			
			List<AviocompanyDTO> sorted = new ArrayList<AviocompanyDTO>();
			
			for(AviocompanyDTO acc : servisi) {
				System.out.println("naziv hotela je" + acc.getName());
			}
			
			String[] paramArray = param.split("=");
			String item = paramArray[0];
			String order = paramArray[1];
			boolean descending=false; 
			boolean ascending=false; 
			
			if(order.equals("descending")) {
				descending = true;
			}
			
			
			if(item.equals("adress") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getAdress))
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
			
			
         if(item.equals("adress") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getAdress).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
         
         
         if(item.equals("name") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getName))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
         
         if(item.equals("name") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getName))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
         
         if(item.equals("name") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getName).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
         
         
         if(item.equals("rate") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getRating))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
         
         if(item.equals("rate") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(AviocompanyDTO::getRating).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
			
		
			return  new ResponseEntity<List<AviocompanyDTO>>(sorted, HttpStatus.OK);
			
		}
	 
	 
		@RequestMapping(value="/getLastWeekReservations/{id}/{dateToday}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FlightReservation>>  getLastWeekReservations(@PathVariable("id") Long idHotela, @PathVariable("dateToday") String od) {
			List<FlightReservation> returnList = new ArrayList<FlightReservation>();
			Set<FlightReservation> pomList = new HashSet<FlightReservation>();
			
			Date today=new Date();
			long ltime=today.getTime()-7*24*60*60*1000;
			Date today_7=new Date(ltime);
			System.out.println("pocetak: " + today_7);
			
			Aviocompany avio = avioRepository.findOneById(idHotela);
			Set<Flight> flights = avio.getFlight();
		//	System.out.println("broj soba " + rooms.size());
			
			for(Flight f: flights) {
				pomList.addAll(this.flightReservationRep.findAllByFlightId(f.getId()));
			}
			
			
			if (!pomList.isEmpty()) {
					for (FlightReservation fff : pomList) {
						if(today.getTime() >= fff.getDatum().getTime() && today_7.getTime()<= fff.getDatum().getTime())
						{
						//	System.out.println("pronsasao1  " + rrr.getStartDate());
							returnList.add(fff);
						} 
						}
					}	
			
		
			return new ResponseEntity<List<FlightReservation>>(returnList,HttpStatus.OK);
		}
		@RequestMapping(value="/getAllReservations/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Set<FlightReservation>>  getAllReservations(@PathVariable("id") Long idHotela) {
			List<ReservationRoom> returnList = new ArrayList<ReservationRoom>();
			Set<FlightReservation> pomList = new HashSet<FlightReservation>();

			Aviocompany hotel = avioRepository.findOneById(idHotela);
			Set<Flight> flights = hotel.getFlight();
			for(Flight f: flights) {
				pomList.addAll(this.flightReservationRep.findAllByFlightId(f.getId()));
			}
			
			

			return new ResponseEntity<Set<FlightReservation>>(pomList,HttpStatus.OK);
		}
		
		@RequestMapping(value="/getAllRatingsHotel/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<RatingAvio>>  getAllRatingsHotel(@PathVariable("id") Long idHotela) {
			List<RatingAvio> returnList = new ArrayList<RatingAvio>();
			Aviocompany hotel = avioRepository.findOneById(idHotela);
			
			returnList = ratingAvioRepository.findAllByAviocompany(hotel);
			
			return new ResponseEntity<List<RatingAvio>>(returnList,HttpStatus.OK);
		}
		
		@RequestMapping(value="/getHotelRevenue/{idHotela}/{od}/{Do}",
						method = RequestMethod.GET)
		public double getHotelRevenue(@PathVariable Long idHotela,@PathVariable String od,@PathVariable String Do){
		
			Set<FlightReservation> pomList = new HashSet<FlightReservation>();

			
			double suma = 0;
			Date dOd = null;
			Date dDo = null;
			java.sql.Date sqlOD = null;
			java.sql.Date sqlDO = null;
			java.sql.Date sqlstart = null;
			try {
				dOd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(od);
				dDo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(Do);
				sqlOD = new java.sql.Date(dOd.getTime());
				sqlDO = new java.sql.Date(dDo.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("od od " + dOd.toString() + dDo.toString());
			System.out.println("od od sql " + sqlOD.toString() + sqlDO.toString());
			Aviocompany aviocompany = avioRepository.findOneById(idHotela);
			Set<Flight> flights = aviocompany.getFlight();	//		System.out.println("broj soba " + rooms.size());
			for(Flight f: flights) {
				pomList.addAll(this.flightReservationRep.findAllByFlightId(f.getId()));
			}
			
			
			if (!pomList.isEmpty()) {
					for (FlightReservation fff : pomList) {
						sqlstart = new java.sql.Date(fff.getDatum().getTime());
						for(Passenger p : fff.getPassengersOnSeats()) {
							
						
						if(sqlDO.getTime() >= sqlstart.getTime() && sqlOD.getTime()<= sqlstart.getTime())
						{
		
							suma= suma + p.getSeat().getPrice();
						} 
						}
					}
					}	
			
			
			return suma;
		
		}
		
	

	
	 
}
