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

import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.SystemDiscount;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class DiscountControllerTest {
private static final String URL_PREFIX = "/discounts";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();	
	}
	
	//kada u bazi postoji samo jedan
	@Test
	public void testGetDiscount() throws Exception{
		
		mockMvc.perform(get(URL_PREFIX + "/getDiscount/2" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(2));
		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testAddPoints() throws Exception {
		
		UserDTO user = new UserDTO();
		
		user.setId((long)1);
		user.setPoints((double) 3);

		String json = TestUtil.json(user);
		this.mockMvc.perform(get(URL_PREFIX + "/addPoints/1/3").contentType(contentType).content(json)).andExpect(status().isOk());
		
		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testChangeDiscount() throws Exception {
		SystemDiscount newSD = new SystemDiscount();
		
		newSD.setId((long)1);
		newSD.setAmount(3.3);
		newSD.setPercent(34.0);
		String json = TestUtil.json(newSD);
		this.mockMvc.perform(post(URL_PREFIX + "/changeDiscount/"+1L).contentType(contentType).content(json)).andExpect(status().isOk());
		
		
	}
}
