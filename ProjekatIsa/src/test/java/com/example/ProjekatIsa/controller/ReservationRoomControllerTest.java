package com.example.ProjekatIsa.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.DTO.RoomDTO;
import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Hotel;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class ReservationRoomControllerTest {
private static final String URL_PREFIX = "/reservationRoom";
	
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
	public void testBookRoom() throws Exception {
		ReservationRoomDTO newResRoom = new ReservationRoomDTO();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   startDate       = format.parse ( "2019-09-28" );
		Date   endDate       = format.parse ( "2019-09-29" );
		
		RoomDTO roomdto = new RoomDTO();
		roomdto.setId((long) 1);
		
		UserDTO userdto = new UserDTO();
		userdto.setId((long)1);
		
		newResRoom.setStartDate(startDate);
		newResRoom.setEndDate(endDate);
		newResRoom.setRoom(roomdto);
		newResRoom.setUser(userdto);
		newResRoom.setCategory("Jednokrevetna");
		newResRoom.setNumPeople((long)1);
		newResRoom.setTotalPrice(222.3);
		
		String json = TestUtil.json(newResRoom);
		this.mockMvc.perform(post(URL_PREFIX + "/bookRoom/1" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}

}
