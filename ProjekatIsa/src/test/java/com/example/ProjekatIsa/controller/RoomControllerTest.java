package com.example.ProjekatIsa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormHotel;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class RoomControllerTest {
private static final String URL_PREFIX = "/rooms";
	
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
	public void testGetAllRooms() throws Exception{
		//sve sobe prvog hotela
		mockMvc.perform(get(URL_PREFIX + "/getAllRooms/1")).andExpect(status().isOk())

		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].number").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].price").value(hasItem((double)50)))
		.andExpect(jsonPath("$.[*].capacity").value(hasItem((double)1)))
		.andExpect(jsonPath("$.[*].room_average_rating").value(hasItem(4.1)))
		.andExpect(jsonPath("$.[*].room_description").value(hasItem("Jednokrevetna")));
		
	}
	@Test
	public void testAddRoom() throws Exception {
		Room newRoom = new Room();
		Hotel h = new Hotel();
		newRoom.setCapacity((double) 2);
		newRoom.setHotel(h);
		newRoom.setNumber(30);
		newRoom.setPrice(33.3);
		newRoom.setRoom_average_rating(2.2);
		newRoom.setRoom_description("Dvokrevetna");
		
		String json = TestUtil.json(newRoom);
		this.mockMvc.perform(post(URL_PREFIX + "/addRoom/"+(long) 1).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testChangeRoom() throws Exception {
		Room newRoom = new Room();
		newRoom.setId((long) 1);
		newRoom.setCapacity((double) 3);
		newRoom.setNumber(44);
		newRoom.setPrice(55.5);
		newRoom.setRoom_average_rating(4.4);
		newRoom.setRoom_description("Apartman");
		

		String json = TestUtil.json(newRoom);
		this.mockMvc.perform(post(URL_PREFIX + "/changeRoom/"+ 1L).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteRoom() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/deleteRoom/"+5L)).andExpect(status().isOk());
	}
	
	@Test
	public void testSearchRooms() throws Exception{
		ReservationRoom newResRoom = new ReservationRoom();
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH); 
		Date startDate = sdf.parse("Sun Sep 29 00:00:00 CEST 2019");
		Date endDate = sdf.parse("Mon Sep 30 00:00:00 CEST 2019");
		newResRoom.setStartDate(startDate);
		newResRoom.setEndDate(endDate);
		newResRoom.setCategory("Jednokrevetna");

		
		String json = TestUtil.json(newResRoom);
		//vracam prvu sobu jer ce odgovarati datim parametrima
		this.mockMvc.perform(post(URL_PREFIX + "/searchRooms/1/-1/-1").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testGetRatingRoom() throws Exception{
		
		//za sobu gdje je id = 1 ocekujem iduce ocjene
		this.mockMvc.perform(get(URL_PREFIX + "/getRatingRoom/8" )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}
	@Test
	public void testCountAverageRating() throws Exception {
		MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/countAverageRating/1")).andExpect(status().isOk())
		.andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		double retVal = Double.parseDouble(resultAsString);
		assertThat(retVal)
		    .isEqualTo(1.00);
	}

}
