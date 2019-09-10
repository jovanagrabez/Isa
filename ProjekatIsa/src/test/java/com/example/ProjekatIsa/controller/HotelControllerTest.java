package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.Hotel;


@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class HotelControllerTest {
	private static final String URL_PREFIX = "/hotels";
	
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
	public void testGetHoteli() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getAll" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("Vojvodina")))
		.andExpect(jsonPath("$.[*].address").value(hasItem("Trg slobode 2, Novi Sad, Srbija")))
		.andExpect(jsonPath("$.[*].description").value(hasItem("U samom centru grada. Stara arhitektura")))
		.andExpect(jsonPath("$.[*].average_rating").value(hasItem(4.3)));
	}
	
	@Test
	@WithUserDetails("hoteladmin@gmail.com")
	public void testDodajHotel() throws Exception {
		Hotel newHotel = new Hotel();
		
		newHotel.setAddress("Stevana Musica 11,Novi Sad,Srbija");
		newHotel.setName("Park");
		newHotel.setDescription("Luksuzan");
		newHotel.setAverage_rating(2.2);
		newHotel.setCity("Novi Sad");

		String json = TestUtil.json(newHotel);
		this.mockMvc.perform(post(URL_PREFIX + "/addHotel" ).contentType(contentType).content(json)).andExpect(status().isCreated());
	}
}
