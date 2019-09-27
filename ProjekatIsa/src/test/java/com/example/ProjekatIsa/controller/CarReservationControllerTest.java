package com.example.ProjekatIsa.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.DTO.CarReservationDTO;
import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.DTO.RoomDTO;
import com.example.ProjekatIsa.DTO.UserDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class CarReservationControllerTest {
	private static final String URL_PREFIX = "/carReservation";
	
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
	public void testReservationCar() throws Exception {
		CarReservationDTO newRes = new CarReservationDTO();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   startDate       = format.parse ( "2019-09-28" );
		Date   endDate       = format.parse ( "2019-09-29" );
		
		CarDTO dto = new CarDTO();
		dto.setId((long) 1);
		
		UserDTO userdto = new UserDTO();
		userdto.setId((long)1);
		
		newRes.setStartDate(startDate);
		newRes.setEndDate(endDate);
		newRes.setCar(dto);
		newRes.setUser(userdto);
		newRes.setCategory("Jednokrevetna");
		newRes.setNumPeople(1);
		newRes.setTotalPrice(222.3);
		
		String json = TestUtil.json(newRes);
		this.mockMvc.perform(post(URL_PREFIX + "/reserveCar/1" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}

}
