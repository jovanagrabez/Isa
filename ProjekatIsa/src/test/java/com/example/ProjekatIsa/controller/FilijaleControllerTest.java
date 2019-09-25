package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

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

import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class FilijaleControllerTest {
	
private static final String URL_PREFIX = "/filijale";
	
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
	public void testIzmeniFilijale() throws Exception {
		Filijale fil = new Filijale();
		
		fil.setId((long) 1);
		fil.setAdresa("Dimitrija Avramovica");
		fil.setDrzava("Srbija");
		fil.setGrad("Novi Sad");
		
		String json = TestUtil.json(fil);
		this.mockMvc.perform(post(URL_PREFIX + "/changeFil/"+1L).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetServis() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getFilijales" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].drzava").value(hasItem("Srbija")));		
		
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testAddFilijale() throws Exception {
		
		Filijale newFil = new Filijale();
		RentACar newR = new RentACar();
		
		newFil.setAdresa("Brace Jovandic 13");
		newFil.setDrzava("Srbija");
		newFil.setGrad("Novi Sad");
		newFil.setRentACar(newR);
		String json = TestUtil.json(newFil);
		this.mockMvc.perform(post(URL_PREFIX + "/addFilijale" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());

	}
	
	@Test
	public void testGetAllCars() throws Exception{
		mockMvc.perform(post(URL_PREFIX + "/getCars/" +1L )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("BMW")))
		.andExpect(jsonPath("$.[*].car_number").value(hasItem("NS-0786")))
		.andExpect(jsonPath("$.[*].price").value(hasItem(500)))
		.andExpect(jsonPath("$.[*].average_rating").value(hasItem(4.1)))
		.andExpect(jsonPath("$.[*].prod_year").value(hasItem(2011)));
		
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDeleteFol() throws Exception {
		this.mockMvc.perform(post(URL_PREFIX + "/deleteFil/"+2L)).andExpect(status().isOk());
	}

}
