package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingControllerTest {
	
	private static final String URL_PREFIX = "/rating";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
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
	
	@Test
	public void testOceneHotelaKorisnika() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/userHotelRating/"+1L )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	
	@Test
	public void testOceneSobeKorisnika() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/userRoomRating/"+1L )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	
	
	@Test
	public void testDodajOcenu() throws Exception {
		
		RatingCar ocena = new RatingCar();
		ocena.setId((long)1);
		ocena.setRate(5);
		Car vozilo = new Car();
		vozilo.setName("Nissan Juke");
		vozilo.setCar_number("NS-123456");
		vozilo.setPrice(300);
		vozilo.setAverage_rating(4.3);
		vozilo.setProd_year(2008);
		User korisnik = new User();
		//korisnik.setFirstName("Jova");
		
		ocena.setUser(korisnik);
		ocena.setCar(vozilo);
		
		String json = TestUtil.json(ocena);
		this.mockMvc.perform(post(URL_PREFIX+"/rateCar").contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}
	
	

}
