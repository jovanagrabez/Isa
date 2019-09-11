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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;


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
	public void testDodajHotel() throws Exception {
		Hotel newHotel = new Hotel();
		
		newHotel.setAddress("Stevana Musica 11,Novi Sad,Srbija");
		newHotel.setName("Park");
		newHotel.setDescription("luksuzan");
		newHotel.setAverage_rating(2.2);
		newHotel.setCity("Novi Sad");

		String json = TestUtil.json(newHotel);
		this.mockMvc.perform(post(URL_PREFIX + "/addHotel" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	@Test
	public void testIzmjeniHotel() throws Exception {
		Hotel hotel = new Hotel();
		List<Room> rooms = new ArrayList<Room>();
		List<AdditionalServiceForHotel> as = new ArrayList<AdditionalServiceForHotel>();
		hotel.setId((long)3);
		hotel.setAddress("Stevana Musica 11,Novi Sad,Srbija");
		hotel.setName("Novi Hotel");
		hotel.setDescription("opis");
		hotel.setAverage_rating(2.2);
		hotel.setRooms(rooms);
		hotel.setAdditional_services(as);
		

		String json = TestUtil.json(hotel);
		this.mockMvc.perform(post(URL_PREFIX + "/changeHotel/"+ 3L).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	 public void obrisiHotel() throws Exception{
	     mockMvc.perform(delete(URL_PREFIX+ "/deleteHotel/"+(long) 3)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	@Test
	public void testDodajSobu() throws Exception {
		Room room = new Room();
		Hotel hotel = new Hotel();
		room.setCapacity((double) 2);
		room.setPrice(25.2);
		room.setRoom_description("Nova soba");
		room.setRoom_average_rating(3.5);
		room.setHotel(hotel);

		String json = TestUtil.json(room);
		this.mockMvc.perform(post("/addRoom/"+(long) 30).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testDodajServis() throws Exception {
		AdditionalServiceForHotel ad = new AdditionalServiceForHotel();
		Hotel hotel = new Hotel();
		ad.setName("Novi servis");
		ad.setPrice(54.5);
		ad.setHotel(hotel);
		String json = TestUtil.json(ad);
		this.mockMvc.perform(post("/addService/"+(long) 3).contentType(contentType).content(json)).andExpect(status().isOk());
	}
}
