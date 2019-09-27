package com.example.ProjekatIsa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.FilijaleDTO;
import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.DTO.RentACarDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormHotel;
import com.example.ProjekatIsa.model.SearchFormServices;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CarReservationRepository;
import com.example.ProjekatIsa.repository.DiscountRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RatingRentACarRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.RatingRentACarService;
import com.example.ProjekatIsa.service.RentalCarService;

@RestController
@RequestMapping(value = "/rentalcars",produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalCarController {
	
	
	@Autowired
	private RentalCarService rentalcarService;
	
	@Autowired 
	private RentalCarRepository rentalcarRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FilijaleRepository filRepository;
	
	@Autowired
	private CarReservationRepository carReseravationRepository;
	
	@Autowired
	private RatingRentACarRepository ratingRentACarRepository;
	
	@Autowired
	private RatingCarRepository ratingCarRepository;
	
	@Autowired DiscountRepository discountRepository;
	
	@Autowired
	private FlightReservationRepository flightRepository;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RentACar>  getRentalCar() {
		
		System.out.println("Number of rent a car services: " + rentalcarService.getAll().size());
		
		return rentalcarService.getAll();
}
	
	@RequestMapping(value="/addService",
			method = RequestMethod.POST)
	public ResponseEntity<?> addNew(@RequestBody RentACarDTO service){
		System.out.println("Dosao u add service");
		
		 RentACar h = rentalcarService.addService(new RentACar(service));
		
	     return new ResponseEntity<RentACar>(h,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(
			value = "/getAllCars/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCars(@PathVariable("id") Long id) {
		List<Car> returnList = new ArrayList<Car> ();
		RentACar h = rentalcarService.findOneById(id);
		returnList = carRepository.findAllByRentacar(h);
		if (returnList!=null) {
	        return new ResponseEntity<List<Car>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(
			value = "/getCars", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@RequestBody Long Id) {
		
		RentACar rentservice = rentalcarService.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByRentacar(rentservice);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	@RequestMapping(
			value = "/getFilijale", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFilijale(@RequestBody Long Id) {
		
		RentACar rentservice = rentalcarService.findOneById(Id);
		
		List<Filijale> returnList = new ArrayList<Filijale>();
		returnList = filRepository.findAllByRentacar(rentservice);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Filijale>>(returnList, HttpStatus.OK);
		}
		
		
}
	
	@RequestMapping(value="/changeService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeService(@RequestBody RentACar newService,@PathVariable("id") Long id){
		
		System.out.println("Usao u change servis");
		
		RentACar oldService = rentalcarService.findOneById(id);
		
		if(oldService!=null) {
			if(newService.getName()!=null) {
				oldService.setName(newService.getName());
			}
			if(newService.getAdress()!=null) {
				oldService.setAdress(newService.getAdress());
			}
			if(newService.getDescription()!=null) {
				oldService.setDescription(newService.getDescription());
			}
			rentalcarRepository.save(oldService);
			
			return new ResponseEntity<RentACar>(oldService,HttpStatus.OK);			
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}		
		
	}
	
	@RequestMapping(value="/deleteService",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteService(@RequestBody Long id) {
		System.out.println("Usao u delete service");
		
		RentACar r = rentalcarService.findOneById(id);
		rentalcarRepository.delete(r);
		return new ResponseEntity<>(HttpStatus.OK);

			
		}
	
	@RequestMapping(value="/addFil/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addFil(@RequestBody FilijaleDTO newFil, @PathVariable("id") Long id){
		System.out.println("Usao u add filijale");
		
		RentACar r = rentalcarService.findOneById(id);
		Filijale f = new Filijale(newFil);
		
		//dodavanje u model
		r.addFilijale(f);
		f.setRentACar(r);
		
		this.filRepository.save(f);
		rentalcarRepository.save(r);
		return new ResponseEntity<>(null, HttpStatus.OK);

		
	}
	
	
	
	//pretraga
		@RequestMapping(value="/searchService",
				method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> searchService(@RequestBody SearchFormServices searchForm){
			System.out.println("Dosao u search ");
			
			List<RentACar> all = rentalcarService.getAll();
			List<RentACar> returnList = new ArrayList<RentACar>();
			List<RentACar> returnList2 = new ArrayList<RentACar>();
			List<RentACar> returnList3 = new ArrayList<RentACar>();

			//pretraga po datumu
			
			if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
				for (RentACar hot : all) {
					System.out.println("usao u prvu petlju : svih servisa");
					System.out.println("servis ima vozila: " + hot.getCar().size());
					//pronalazim sve sobe hotela
					List<Car> car = carRepository.findAllByRentacar(hot);
					if (car.size()>0) {
						int num = car.size();
						System.out.println("broj soba " + car.size());
						for (Car r : car) {
							if(reserved(r, searchForm.getStartDate(), searchForm.getEndDate())) {
								System.out.println("umanjujem sobu");
								--num;
							}
						}
						if(num!=0) {
							System.out.println("Dodajem hotel");
							returnList3.add(hot);
						}
					}
				}
			}else {
				returnList3 = rentalcarService.getAll();
			}

			//ako se pretrazuje po nazivu
			if (searchForm.getName() != null) {
				for (RentACar h : returnList3) {
					if (h.getName().contains(searchForm.getName())) {
						returnList.add(h);
					}
				}
				//ako se pretrazuje i po nazivu i po gradu
				if (searchForm.getCity() != null) {
					for (RentACar h : returnList) {
						if (h.getCity().equals(searchForm.getCity())) {
							returnList2.add(h);
						}
					}
					return new ResponseEntity<List<RentACar>>(returnList2, HttpStatus.OK);
				}
				//ako se pretrazuje samo po nazivu
				else {
					return new ResponseEntity<List<RentACar>>(returnList, HttpStatus.OK);
				}
			}
			//ako se ne pretrazuje po nazivu nego samo gradu 
			else {
				if (searchForm.getCity() != null) {
					for (RentACar h : returnList3) {
						if (h.getCity().equals(searchForm.getCity())) {
							returnList.add(h);
						}
					}
					return new ResponseEntity<List<RentACar>>(returnList, HttpStatus.OK);
				}
				//ako ne pretrazuje ni po nazivu ni po gradu
				return new ResponseEntity<List<RentACar>>(all, HttpStatus.OK);
			}
		}
		
		
		
		
		@RequestMapping(value="/searchFast/{id}",
				method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> searchFast(@RequestBody SearchFormServices searchForm, @PathVariable("id") Long id){
			System.out.println("Dosao u search ");
			
			FlightReservation help = new FlightReservation();
			
			help = flightRepository.findOneById(id);
			if (help!=null) {
				if (help.getDatum().getTime()>searchForm.getStartDate().getTime()) {
					 //return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					return new ResponseEntity<>(null, HttpStatus.OK);
				}
			}
			List<Discount> povratna= new ArrayList<Discount>();
			
			List<RentACar> all = rentalcarService.getAll();
			List<RentACar> returnList = new ArrayList<RentACar>();
			List<RentACar> returnList2 = new ArrayList<RentACar>();
			List<RentACar> returnList3 = new ArrayList<RentACar>();

			//pretraga po datumu
			
			if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
				for (RentACar hot : all) {
					System.out.println("usao u prvu petlju : svih servisa");
					System.out.println("servis ima vozila: " + hot.getCar().size());
					//pronalazim sve sobe hotela
					List<Car> car = carRepository.findAllByRentacar(hot);
					if (car.size()>0) {
						int num = car.size();
						System.out.println("broj soba " + car.size());
						for (Car r : car) {
							if(reserved(r, searchForm.getStartDate(), searchForm.getEndDate())) {
								System.out.println("umanjujem sobu");
								--num;
							}
						}
						if(num!=0) {
							System.out.println("Dodajem hotel");
							returnList3.add(hot);
						}
					}
				}
			}else {
				returnList3 = rentalcarService.getAll();
			}

			//ako se pretrazuje po nazivu
			if (searchForm.getName() != null) {
				for (RentACar h : returnList3) {
					if (h.getName().contains(searchForm.getName())) {
						returnList.add(h);
					}
				}
				//ako se pretrazuje i po nazivu i po gradu
				if (searchForm.getCity() != null) {
					for (RentACar h : returnList) {
						if (h.getCity().equals(searchForm.getCity())) {
							returnList2.add(h);
						}
					}
	//				return new ResponseEntity<List<RentACar>>(returnList2, HttpStatus.OK);
				}
				//ako se pretrazuje samo po nazivu
				else {
		//			return new ResponseEntity<Set<RentACar>>(returnList, HttpStatus.OK);
				}
			}
			//ako se ne pretrazuje po nazivu nego samo gradu 
			else {
				if (searchForm.getCity() != null) {
					for (RentACar h : returnList3) {
						if (h.getCity().equals(searchForm.getCity())) {
							returnList.add(h);
						}
					}
					
					returnList3 = returnList;
					
			
				}
				
				else {
					returnList3 = all;
				}
				
			}
			
			
			for(RentACar rent: returnList) {
				List<Discount> auta = this.discountRepository.findAllByRentacar(rent);
				povratna.addAll(auta);
			}
			
			
			return new ResponseEntity<List<Discount>>(povratna, HttpStatus.OK);
		}
		
		
		
		
		
		private boolean reserved(Car r, Date startDate, Date endDate) {
			// TODO Auto-generated method stub
			List<CarReservation> resRoom = carReseravationRepository.findAllByCar(r);
			
			for(CarReservation reservation : resRoom) {
				
				if(endDate.getTime() >= reservation.getStartDate().getTime() && endDate.getTime()<= reservation.getEndDate().getTime())
				{
					return true;
				} else if(startDate.getTime() >= reservation.getStartDate().getTime() && startDate.getTime() <= reservation.getEndDate().getTime())
				{
					return true;
				}
			}
			return false;
		}


		@RequestMapping(value="/sortForm/{param}",
				method = RequestMethod.POST)
		public ResponseEntity<?> sortForm(@PathVariable("param") String param, @RequestBody List<RentACarDTO> servisi){
			System.out.println("usao u sortiranjee");
			
			List<RentACarDTO> sorted = new ArrayList<RentACarDTO>();
			
			for(RentACarDTO acc : servisi) {
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
						  .sorted(Comparator.comparing(RentACarDTO::getAdress))
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
			
			
            if(item.equals("adress") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getAdress).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
            
            
            if(item.equals("name") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getName))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
            
            if(item.equals("name") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getName))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
            
            if(item.equals("name") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getName).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
            
            
            if(item.equals("rate") && order.equals("ascending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getAverage_rating))
						  .collect(Collectors.toList());
				
				System.out.println("Name" + sorted);
			}
            
            if(item.equals("rate") && order.equals("descending") ) {
				
				sorted = servisi.stream()
						  .sorted(Comparator.comparing(RentACarDTO::getAverage_rating).reversed())
						  .collect(Collectors.toList());
				
				System.out.println("Adresa" + sorted);
			}
			
		
			return  new ResponseEntity<List<RentACarDTO>>(sorted, HttpStatus.OK);
			
		}
		
		
		
		@RequestMapping(value="/getLastWeekReservations/{id}/{dateToday}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<CarReservation>>  getLastWeekReservations(@PathVariable("id") Long idServisa, @PathVariable("dateToday") String od) {
			List<CarReservation> returnList = new ArrayList<CarReservation>();
			List<CarReservation> pomList = new ArrayList<CarReservation>();
			
			Date today=new Date();
			long ltime=today.getTime()-7*24*60*60*1000;
			Date today_7=new Date(ltime);
			System.out.println("pocetak: " + today_7);
			
			RentACar services = rentalcarService.findOneById(idServisa);
			List<Car> cars = carRepository.findAllByRentacar(services);
			System.out.println("broj soba " + cars.size());
			if (!cars.isEmpty()) {
				for (Car c : cars) {
					System.out.println("broj rezervacija bilo kakvih " + c.getReservation().size());
					//proba
					if (!c.getReservation().isEmpty()) {
					for (CarReservation rrr : c.getReservation()) {
						System.out.println("proba  " + rrr.getStartDate());
						if(today.getTime() >= rrr.getStartDate().getTime() && today_7.getTime()<= rrr.getStartDate().getTime())
						{
							System.out.println("pronsasao1  " + rrr.getStartDate());
							returnList.add(rrr);
						} 
						}
					}	
				}
			}
		
			return new ResponseEntity<List<CarReservation>>(returnList,HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/getAllReservations/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<CarReservation>>  getAllReservations(@PathVariable("id") Long idServisa) {
			List<CarReservation> returnList = new ArrayList<CarReservation>();
			
			RentACar services = rentalcarService.findOneById(idServisa);
			List<Car> cars = carRepository.findAllByRentacar(services);
			System.out.println("broj soba " + cars.size());
			if (!cars.isEmpty()) {
				for (Car r : cars) {
					System.out.println("broj rezervacija bilo kakvih " + r.getReservation().size());
					//proba
					if (!r.getReservation().isEmpty()) {
						for (CarReservation rrr : r.getReservation()) {
							returnList.add(rrr);
						}
					}
				}
			}

			return new ResponseEntity<List<CarReservation>>(returnList,HttpStatus.OK);
		}
		
		
		
		@RequestMapping(value="/getServiceRevenue/{idServisa}/{od}/{Do}",
				method = RequestMethod.GET)
		public double getHotelRevenue(@PathVariable Long idServisa,@PathVariable String od,@PathVariable String Do){

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
					RentACar service = rentalcarService.findOneById(idServisa);
					List<Car> cars = carRepository.findAllByRentacar(service);
					System.out.println("broj soba " + cars.size());
					if (!cars.isEmpty()) {
						for (Car r : cars) {
							System.out.println("broj rezervacija bilo kakvih " + r.getReservation().size());
							//proba
							if (!r.getReservation().isEmpty()) {
							for (CarReservation rrr : r.getReservation()) {
								sqlstart = new java.sql.Date(rrr.getStartDate().getTime());
								System.out.println("proba  " + sqlstart);
								if(sqlDO.getTime() >= sqlstart.getTime() && sqlOD.getTime()<= sqlstart.getTime())
								{
									System.out.println("pronsasao1  " + rrr.getStartDate());
									suma+=rrr.getTotalPrice();
								} 
								}
							}	
						}
					}
	
						return suma;

			}
		
		
		@RequestMapping(value="/getAllRatingsService/{id}",
				method = RequestMethod.GET
				)
		public ResponseEntity<List<RatingRentACar>>  getAllRatingsService(@PathVariable("id") Long idServisa) {
			
			List<RatingRentACar> returnList = new ArrayList<RatingRentACar>();
			RentACar service = rentalcarService.findOneById(idServisa);
			
			returnList = ratingRentACarRepository.findAllByRentacar(service);
			System.out.println("Id servisa "+ idServisa);
			System.out.println("ocjene " + returnList.size());
			
			return new ResponseEntity<List<RatingRentACar>>(returnList,HttpStatus.OK);
		}
	}


