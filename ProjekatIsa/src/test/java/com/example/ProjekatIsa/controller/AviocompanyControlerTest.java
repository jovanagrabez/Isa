package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class AviocompanyControlerTest {
	
	
private static final String URL_PREFIX = "/avioCompany";
	
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
	public void testGetAvioCompany() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getAll" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("AirN")))
		.andExpect(jsonPath("$.[*].adress").value(hasItem("Aerodrom Beograd 59, Beograd, Srbija")))
		.andExpect(jsonPath("$.[*].description").value(hasItem("opis")))
		.andExpect(jsonPath("$.[*].rating").value(hasItem(4)));
	}
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void testDodajAviokompaniju() throws Exception {
		AviocompanyDTO aviocompany = new AviocompanyDTO();
		
		aviocompany.setAdress("Stevana Musica 11,Novi Sad,Srbija");
		aviocompany.setName("WizzAir");
		aviocompany.setDescription("opis");
		aviocompany.setRating(2.2);
		

		String json = TestUtil.json(aviocompany);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void testIzmjeniAviokompaniju() throws Exception {
		Aviocompany aviocompany = new Aviocompany();
		Set<Destination> d = new HashSet<Destination>();
		Set<Flight> f= new HashSet<Flight>();
		aviocompany.setFlight(f);
		aviocompany.setDestination(d);
		aviocompany.setId((long)3);
		aviocompany.setAdress("Stevana Musica 11,Novi Sad,Srbija");
		aviocompany.setName("AirN");
		aviocompany.setDescription("opis");
		aviocompany.setRating(2.2);
		

		String json = TestUtil.json(aviocompany);
		this.mockMvc.perform(put(URL_PREFIX + "/update").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	 public void obrisiPostojecuKompanijuTest() throws Exception{
	     mockMvc.perform(delete(URL_PREFIX+ "/"+(long) 3)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void testDodajDestinaciju() throws Exception {
		Destination destination = new Destination();
		
		destination.setCountry("Belgija");
		destination.setName("Brisel");
		destination.setDescription("from");
		
		

		String json = TestUtil.json(destination);
		this.mockMvc.perform(post("/destination").contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void testDodajDestinacijuFalse() throws Exception {
		Destination destination = new Destination();
		
		destination.setCountry("Belgija");
		destination.setDescription("from");
		
		

		String json = TestUtil.json(destination);
		this.mockMvc.perform(post("/destination").contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
	 public void obrisiDestinacijuKompanijeTest() throws Exception{
	     mockMvc.perform(delete("/destination/"+(long) 1 + "/" + (long)1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	
	

}

