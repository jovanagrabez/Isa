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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;

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
		mockMvc.perform(get(URL_PREFIX + "/getAllRooms/" +1L)).andExpect(status().isOk())

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
}
