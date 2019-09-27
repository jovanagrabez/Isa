package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.DTO.CarReservationDTO;
import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.DiscountHotel;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.PricingCar;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormServices;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CarReservationRepository;
import com.example.ProjekatIsa.repository.DiscountRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.PricingCarRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.FilijaleService;
import com.example.ProjekatIsa.service.PricingCarService;
import com.example.ProjekatIsa.service.RentalCarService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	FilijaleService filService;
	
	@Autowired
	FilijaleRepository filRepository;
	
	@Autowired
	RentalCarService rentcarService;
	
	@Autowired
	RentalCarRepository rentRepository;
	
	@Autowired 
	DiscountRepository discountRepository;
	
	@Autowired
	RatingCarRepository ratingCarRepository;
	
	@Autowired 
	CarReservationRepository carResRepository;
	
	@Autowired
	private FlightReservationRepository flightRepository;
	
	@Autowired
	private PricingCarService pricingService;
	
	@RequestMapping(
			value = "/getDiscountCars/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Discount>>  getDiscountCars(@PathVariable("id") Long id) {
		
		RentACar rent = rentRepository.findOneById(id);
		List<Discount> pomocni = discountRepository.findAllByRentacar(rent);
		
		System.out.println("Pronasao je discount vozila" + pomocni);
		return new ResponseEntity<List<Discount>>(pomocni, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addCar",method = RequestMethod.POST)
	public ResponseEntity<?> addNewCar(@RequestBody CarDTO car){
		System.out.println("dosaooo u addCar");
		Car c = this.carService.addCar(new Car(car));
		return new ResponseEntity<Car>(c,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deleteCar/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
		System.out.println("Usao u delete service");
		
		Car car = carRepository.findOneById(id);
		List<CarReservation> res = new ArrayList<CarReservation>();
		res = carResRepository.findAllByCar(car);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();    
		String danasString = dateFormat.format(today);
		
		Date danasDatum=null;
		Date kraj=null;
		try {
			danasDatum = dateFormat.parse(danasString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		System.out.println("Danaas " + danasDatum);
		
		for(CarReservation carr : res) {
			Date end = carr.getEndDate();
			String stringkraja = dateFormat.format(end);
			try {
				kraj = dateFormat.parse(stringkraja);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(danasDatum.before(kraj)) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			else {
				
			}
			
		}
		
		
		carRepository.delete(car);
		return new ResponseEntity<>(HttpStatus.OK);

			
		}
	
	@RequestMapping(
			value = "/getCars", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@RequestBody Long Id) {
		
		Filijale fil = filService.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByFilijale(fil);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll(@RequestBody Long Id) {
		
		Car car = new Car();
		RentACar servis = rentRepository.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByRentacar(servis);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	
	
	@RequestMapping(
			value = "/getCarById", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCarById(@RequestBody Long Id) {
		System.out.println("Usaoooo");
		Car car = new Car();
		Discount discount = discountRepository.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByDiscount(discount);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	@RequestMapping(value="changeCar/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeFil(@RequestBody CarDTO newCar,@PathVariable("id") Long id){
		
		System.out.println("Usao u change car");
		
		Car old = carRepository.findOneById(id);
		Car car2 = new Car(newCar);
		
			if(newCar.getName()!=null) {
				old.setName(car2.getName());
			}
			if(newCar.getCar_number()!=null) {
				old.setCar_number(car2.getCar_number());
			}
			if(newCar.getPrice()!= 0) {
				old.setPrice(car2.getPrice());
			}
			
			try {
			
			carRepository.save(old);
			
			return new ResponseEntity<>(null,HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	
	
	@RequestMapping(value="/searchCar/{id}/{cenaOd}/{cenaDo}",method=RequestMethod.POST)
	public ResponseEntity<?> searchCar(@RequestBody CarReservation carReservation,
										   @PathVariable("id") Long id,
			                               @PathVariable("cenaOd") Long cenaOd,
			                               @PathVariable("cenaDo") Long cenaDo){
		 
		   System.out.println("Dosao u search cars");

		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
	                Locale.ENGLISH); 
		   Date startDate = null;
		   Date endDate = null;
		   
		   try {
			   startDate = sdf.parse(carReservation.getStartDate().toString());
			   endDate = sdf.parse(carReservation.getEndDate().toString());
		   } catch (ParseException e) {
			   e.printStackTrace();
			   
		   }
  
		
		   RentACar servis = rentcarService.findOneById(id);
		   List<Car> allCars = carRepository.findAllByRentacar(servis);   
		   List<Car> returnList = new ArrayList<Car>();
		   
		   
		   List<CarDTO> carsfromServiceDTO = new ArrayList<>();
		   

			   
			   for(Car cars : allCars) {
				   
				   List<CarReservation> reservation = cars.getReservation();
				   boolean free = true;
				   
				   int reserved = 0;
				   if(!reservation.isEmpty()) {
				   for(CarReservation res : reservation) {
					   
					   free = checkforfree(res, startDate, endDate);
					   
					   if(!free)
					   {
						   reserved++;
					   }
					   
				   }
		      }
				   
				   if(reserved==0)
				   {
					   //cena nije oznacena u pretrazi
					   if(cenaOd == -1 && cenaDo==-1)
					   {
						   if(cars.getCategory().getMark().equals(carReservation.getCategory()))
						   {
							   returnList.add(cars);	
						   }
					   }
					   else if(cenaOd==-1)
					   {
						   if(cars.getCategory().getMark().equals(carReservation.getCategory()) && cars.getPrice() <= cenaDo)
						   {
							   returnList.add(cars);	   
						   }
						   
					   }
					   else if(cenaDo==-1)
					   {
						   if(cars.getCategory().getMark().equals(carReservation.getCategory()) && cars.getPrice() >= cenaOd)
						   {
							   returnList.add(cars);	   
						   }
					   }
					   else {
						   if(cars.getCategory().getMark().equals(carReservation.getCategory()) && cars.getPrice() >= cenaOd && cars.getPrice() <= cenaDo)
						   {
							   returnList.add(cars);	   
						   }
						   
					   }
					   //Car dto = new Car();
					   //carsfromService.add(cars);
				   }
				   
				   
			   }
		  
		   
//		   for(Car cars : carsfromService) {
//			   System.out.println("Vozilo" + cars.getName() + "" + cars.getReservation().size());
//			   carsfromServiceDTO.add(new CarDTO(cars));
//		   }
//		   
//		   
//		    System.out.println("pronadjena vozila" + carsfromService.size());	
				//racunanjen totalne cijene
				for(Car r: returnList) {
					if (!returnList.isEmpty()) {
						Double totalPrice = countPriceInSearchCars(r,startDate,endDate);
						System.out.println("totalna cijena je : "+ totalPrice);
						r.setTotalPrice(totalPrice);
					}
				}
		    returnList = nonDiscountCars(returnList, startDate, endDate);
		    return new ResponseEntity<List<Car>>(returnList,HttpStatus.OK);
	}
	
	
	public boolean checkforfree(CarReservation res, Date startDate, Date endDate) {
		
		//provjeravamo datum koji smo unijeli za preuzimanje vozila
		//ako je on nakon datuma vracanja vozila koji je registrovan -ok
		if(startDate.getTime() >= res.getEndDate().getTime()) {
			return true;
			
		}
		else {
			if(endDate.getTime() <= res.getStartDate().getTime())
			{
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	@RequestMapping(value="/getRatingCar/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RatingCar>>  getRatingCar(@PathVariable("id") Long idCar) {

		List<RatingCar> returnList = new ArrayList<RatingCar>();
		Car car = carService.findOneById(idCar);
		returnList = ratingCarRepository.findAllByCar(car);
		
		return new ResponseEntity<List<RatingCar>>(returnList,HttpStatus.OK);

	}
	
	@RequestMapping(value="/searchFast/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchFast(@RequestBody SearchFormServices searchForm,@PathVariable("id") Long id){
		System.out.println("Dosao u search faaaaaaaaaast");
		
		FlightReservation help = new FlightReservation();
		help = flightRepository.findOneById(id);
		if (help!=null) {
			if (help.getDatum().getTime()>searchForm.getStartDate().getTime()) {
				 //return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		}
		List<Discount> povratna= new ArrayList<Discount>();
		List<RentACar> all = rentRepository.findAll();
		List<RentACar> returnList = new ArrayList<RentACar>();
		
		List<RentACar> returnList2 = new ArrayList<RentACar>();
		List<RentACar> returnList3 = new ArrayList<RentACar>();
		List<Discount> pomocna1= new ArrayList<Discount>();

		//ako se pretrazuje po nazivu
				if (searchForm.getName() != null) {
					for (RentACar h : all) {
						if (h.getName().contains(searchForm.getName())) {
							returnList.add(h);
						}
						returnList3 = returnList;
					}
					//ako se pretrazuje i po nazivu i po gradu
					if (searchForm.getCity() != null) {
						for (RentACar h : returnList) {
							if (h.getCity().equals(searchForm.getCity())) {
								returnList2.add(h);
							}
						}
						returnList3 = returnList2;
					}
				}
				//ako se ne pretrazuje po nazivu nego samo gradu 
				else {
					if (searchForm.getCity() != null) {
    					for (RentACar h : all) {
							if (h.getCity().equals(searchForm.getCity())) {
								returnList.add(h);	
							}
						}
						returnList3 = returnList;
					}
					
					//ni po nazivu ni po gradu
					returnList3 = all;
				}
		
		//pretraga po datumu
		
		if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
			System.out.println("u trecoj lisri ima servisa: " +returnList3.size() );
			for (RentACar hot : returnList3) {
				 boolean free = true;
				 
				System.out.println("usao u prvu petlju : svih servisa");
				//pronalazim sve sobe hotela
				pomocna1 = discountRepository.findAllByRentacar(hot);
				int reserved = pomocna1.size();
				
				if (!pomocna1.isEmpty()) {			
					for(Discount dh : pomocna1) {
						free = checkforfreeDH(dh, searchForm.getEndDate(), searchForm.getStartDate());
						if (!free) {
							povratna.add(dh);
						}
					}
				}

			}


		}

		
		return new ResponseEntity<List<Discount>>(povratna, HttpStatus.OK);
	}
	
	
		public boolean checkforfreeDH(Discount res, Date endDate, Date startDate) {
				
				//provjeravamo datum koji smo unijeli za preuzimanje vozila
				//ako je on nakon datuma vracanja vozila koji je registrovan -ok
				if(startDate.getTime() >= res.getDateTo().getTime()) {
					return true;
					
				}
				else {
					if(endDate.getTime() <= res.getDateFrom().getTime())
					{
						return true;
					}
					else {
						return false;
					}
				}
			}
		
		
		//Izbaci vozila koje su na popustu
		 List<Car> nonDiscountCars (List<Car> cars, Date startDate, Date endDate){
				System.out.println("nonDiscountCars ");
				List<Car> returnList = new ArrayList<Car>();
				returnList = cars;
				if (returnList.size()>0) {
					System.out.println("broj" + returnList.size());

				for (int i = 0; i < returnList.size(); i++) {
					Car r = returnList.get(i);
					 if(returnList.size()>0) {
						System.out.println("kroz vozila ");
						
						List<Discount> discount = discountRepository.findAllByCar(r);
						if (!discount.isEmpty()) {
							System.out.println("postoji diskaunt ");
							System.out.println("DISCOUNT" + discount.size());
							for(Discount d : discount) {
								if (d.getDateFrom().getTime()<startDate.getTime() && d.getDateTo().getTime()>endDate.getTime()) {
									System.out.println("uklanjam vozilo ");
									returnList.remove(r);
									returnList.iterator().next();
									if(returnList.isEmpty()) {
										System.out.println("PRAZNA JE");
										return returnList;

									}
								
								}
							}
						}
					}
				}
		    }	
				return returnList;
			}
		
		@RequestMapping(
				value="/chekIfFlightIsBooked/{idRes}",
				method = RequestMethod.POST,
				produces = MediaType.TEXT_PLAIN_VALUE)
		public String chekIfFlightIsBooked(@RequestBody CarReservationDTO carRes,
											@PathVariable Long idRes){
			
			System.out.println("dosao u cekiraj jel bukiran flajt tad");
			Date startDate = null;
			startDate = carRes.getStartDate();
			
			FlightReservation help = new FlightReservation();
			
			help = flightRepository.findOneById(idRes);
			if (help!=null) {
				
				// provjeravamo da li je let prije rezervacije i provjeravamo broj putnika
				//datum pocetka rez hotela mora biti veci ili jednak datumu leta
				if (help.getDatum().getTime()>startDate.getTime()) {
					 //return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					return Boolean.TRUE.toString();
				}
				//broj ljudi u letu morra biti veci ili jednak broju gostiju u hotelu
				if (carRes.getNumPeople() > (double) help.getNumPass()) {
					//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					return Boolean.TRUE.toString();
				}
			}
			//return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			return Boolean.FALSE.toString();
			
		}
		
		
		public Double countAveragePricing(Car r) {
			
			Double returnValue = 0.0;
			Double suma = 0.0;
			
			List<PricingCar> pomList = pricingService.findAllByCar(r);
			if(!pomList.isEmpty()) {
				for (PricingCar p : pomList) {
					suma += p.getPrice();
					System.out.println("suma " + suma);
				}
				System.out.println("djelim sa " + pomList.size());
					returnValue = suma/pomList.size();
				
			}
			System.out.println("dobio sam sa " + Double.parseDouble(new DecimalFormat("##.#").format(returnValue)));
			return Double.parseDouble(new DecimalFormat("##.#").format(returnValue));
			//return returnValue;
		}
		
		
		
		//racunanje konacne cijene za pretrazena vozila
		public Double countPriceInSearchCars(Car r, Date startDate, Date endDate) {
			Double returnValue = 0.0;
			Double returnValue1 = 0.0;

			long brojDana = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
			System.out.println("broj dana " + brojDana);
			List<PricingCar> pomList = pricingService.findAllByCar(r);
			if(pomList!=null) {
				for (PricingCar p : pomList) {
					//System.out.println("kroz jedan prajsing");
					// pronasla cjenovnik u kome se nalazi pocetni datum
					if (startDate.getTime()<=p.getDateTo().getTime() && startDate.getTime()>=p.getDateFrom().getTime()) {
						if (endDate.getTime()<=p.getDateTo().getTime() && endDate.getTime()>=p.getDateFrom().getTime()) {
							//ako se i kraj i pocetak nalaze u istom cjenovnkiku
							System.out.println("Nalaze se u istom cjenovniku");
							returnValue = brojDana * p.getPrice();
							return Double.parseDouble(new DecimalFormat("##.##").format(returnValue));
						}
						else {
							//ako se ne nalaze u isstom cjenovniku
							//racunam za prvi cjenovnik
							System.out.println(" ne Nalaze se u istom cjenovniku prvi");
							long brojDanaPrviCj = ChronoUnit.DAYS.between(startDate.toInstant(), p.getDateTo().toInstant());
							//System.out.println("broj dana 1 " + (brojDanaPrviCj+1));
							returnValue1 = (brojDanaPrviCj+1) * p.getPrice();
						//	System.out.println("cena1 "+ returnValue1);
							
						}
						
						
					}
					//racunam za drugi
					if (endDate.getTime()<=p.getDateTo().getTime() && endDate.getTime()>=p.getDateFrom().getTime()) {
						System.out.println("ne nalaze se u istom cjenovniku drugi ");
						long brojDanaDrugiCj = ChronoUnit.DAYS.between(p.getDateFrom().toInstant(), endDate.toInstant());
						//System.out.println("broj dana 2 " + (brojDanaDrugiCj));
						Double returnValue2 = (brojDanaDrugiCj )* p.getPrice();
						returnValue = returnValue1 + returnValue2;
						//System.out.println("cena2 "+ returnValue2);
					}
				}
			
			}
			System.out.println("cena "+ returnValue);
			return Double.parseDouble(new DecimalFormat("##.##").format(returnValue));
		}
	
	
	
	

}
