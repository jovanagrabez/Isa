package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.RatingRentACarService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingControllerTest {
	
	private static final String URL_PREFIX = "/rating";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepostory;
	
	@Autowired
	private FilijaleRepository filRepostory;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();	
	}
	
	@Test
	public void testOceneVozilaKorisnika() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/userCarRating/"+1L )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	
	@Test
	public void testOceneServisaKorisnika() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/userServiceRating/"+1L )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	
//	@Test
//	public void testOceneHotelaKorisnika() throws Exception {
//		mockMvc.perform(get(URL_PREFIX + "/userHotelRating/"+1L )).andExpect(status().isOk())
//		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
//		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
//	}
	
	@Test
	public void testOceneSobeKorisnika() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/userRoomRating/"+1L )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajOcenu() throws Exception {
		
		RatingCar ocena = new RatingCar();
		ocena.setId((long)1);
		ocena.setRate(5);
		Car vozilo = new Car();
		vozilo.setId((long) 1);
		vozilo.setName("Nissan Juke");
		vozilo.setCar_number("NS-123456");
		vozilo.setPrice(300);
		vozilo.setAverage_rating(4.3);
		vozilo.setProd_year(2008);
		User korisnik = new User();
		korisnik = this.userRepository.findOneById((long) 1);
		//korisnik.setFirstName("Jova");
		
		ocena.setUser(korisnik);
		ocena.setCar(vozilo);
		
		String json = TestUtil.json(ocena);
		this.mockMvc.perform(post(URL_PREFIX+"/rateCar").contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajOcenuServisu() throws Exception {
		
		RatingRentACar ocena = new RatingRentACar();
		ocena.setId((long)1);
		ocena.setRate(5);
		RentACar service = new RentACar();
		service.setId((long) 1);
		service.setName("Novi servis");
		service.setAdress("Nova adresica");
		service.setCity("Novi Sad");
		service.setAverage_rating(4.3);
		service.setDescription("Najj servis u gradu");
		
		List<Car> car = new ArrayList<Car>();
		car = carRepostory.findAll();
		
		List<Filijale> fil = new ArrayList<Filijale>();
		fil = filRepostory.findAll();
		
		service.setCar(car);
		service.setFilijale(fil);
		User user = new User();
		user = userRepository.findOneById((long)1);
	
		service.setCar(car);
		
		ocena.setCar(service);
		ocena.setUser(user);
		
		String json = TestUtil.json(ocena);
		this.mockMvc.perform(post(URL_PREFIX+"/rateService/"+1L).contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajOcenuSobe() throws Exception {
		
		RatingRoom ocena = new RatingRoom();
		ocena.setId((long)1);
		ocena.setRate(5);
		Room room = new Room();
		room.setId((long)5);
		room.setCapacity(3.0);
		room.setNumber(5);
		room.setPrice(150.0);
		room.setRoom_average_rating(4.4);
		room.setRoom_description("Apartman");
		User user = new User();
		user = userRepository.findOneById((long)1);
		
		ocena.setRoom(room);
		ocena.setUser(user);
		
		String json = TestUtil.json(ocena);
		this.mockMvc.perform(post(URL_PREFIX+"/rateRoom").contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}
	
	

}
