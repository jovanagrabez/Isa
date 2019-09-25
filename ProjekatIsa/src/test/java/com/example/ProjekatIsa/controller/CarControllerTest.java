package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.ReservationRoom;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class CarControllerTest {
	
	private static final String URL_PREFIX = "/car";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();	
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDodajVozilo() throws Exception {
		Car newCar = new Car();
		
		newCar.setName("Ficko");
		newCar.setCar_number("Reg-123");
		newCar.setAverage_rating(3.1);
		newCar.setPrice(100);
		newCar.setProd_year(2005);
		
		String json = TestUtil.json(newCar);
		this.mockMvc.perform(post(URL_PREFIX + "/addCar" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());

		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testObrisiVozilo() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/deleteCar/"+1L)).andExpect(status().isOk());
	}
	
		
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniVozilo() throws Exception {
		Car newCar = new Car();
		
		newCar.setName("Audi");
		newCar.setCar_number("NS-123");
		newCar.setAverage_rating(4.1);
		newCar.setPrice(250);
		newCar.setProd_year(2012);
		
		newCar.setId((long) 2);
		
		String json = TestUtil.json(newCar);
		this.mockMvc.perform(post(URL_PREFIX + "/changeCar/"+2L).contentType(contentType).content(json)).andExpect(status().isOk());
		
		
	}
	
	@Test
	public void testSearchCars() throws Exception{
		CarReservation newRes = new CarReservation();
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH); 
		Date startDate = sdf.parse("Sun Sep 29 00:00:00 CEST 2019");
		Date endDate = sdf.parse("Mon Sep 30 00:00:00 CEST 2019");
		newRes.setStartDate(startDate);
		newRes.setEndDate(endDate);
		

		
		String json = TestUtil.json(newRes);
		//vracam prvu sobu jer ce odgovarati datim parametrima
		this.mockMvc.perform(post(URL_PREFIX + "/searchCar/1/-1/-1").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
//	@Test
//	public void testGetRatingCar() throws Exception{
//		
//		//za sobu gdje je id = 1 ocekujem iduce ocjene
//		this.mockMvc.perform(get(URL_PREFIX + "/getRatingCar/8" )).andExpect(status().isOk())
//		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
//		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
//	}

}
