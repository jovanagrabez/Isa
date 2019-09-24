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
import java.util.ArrayList;
import java.util.List;

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
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class AddServiceControllerTest {
	private static final String URL_PREFIX = "/addServices";
	
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
	public void testGetAllServices() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getAllServices/" +1L)).andExpect(status().isOk())

		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(2)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("Parking")))
		.andExpect(jsonPath("$.[*].price").value(hasItem(30.0)));
	}
	
	@Test
	public void testAddServices() throws Exception {
		AdditionalServiceForHotel newAddService = new AdditionalServiceForHotel();
		Hotel h = new Hotel();
		newAddService.setName("Novi servis");
		newAddService.setPrice(12.1);
		newAddService.setHotel(h);
		
		String json = TestUtil.json(newAddService);
		this.mockMvc.perform(post(URL_PREFIX + "/addService/"+(long) 3).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testChangeService() throws Exception {
		AdditionalServiceForHotel newAddService = new AdditionalServiceForHotel();
		newAddService.setId((long)1);
		newAddService.setName("Tranfer u jednom smeru");
		newAddService.setPrice(30.5);
		

		String json = TestUtil.json(newAddService);
		this.mockMvc.perform(post(URL_PREFIX + "/changeService/"+ 1L).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	@Test
	public void testDeleteService() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/deleteService/"+5L)).andExpect(status().isOk());
	}
}
