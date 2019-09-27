package com.example.ProjekatIsa.controller;

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

import com.example.ProjekatIsa.DTO.PricingCarDTO;
import com.example.ProjekatIsa.DTO.PricingDTO;
import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.PricingCar;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class PricingCarControllerTest {
	private static final String URL_PREFIX = "/pricingcar";
	
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
	public void testGetAllPricing() throws Exception{
		//sve sobe prvog servisa
		mockMvc.perform(get(URL_PREFIX + "/getAllPricing/1")).andExpect(status().isOk())

		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].price").value(hasItem((double)50)));
		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testAddPricing() throws Exception {
		PricingCar pricing = new PricingCar();

		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH); 
		Date startDate = sdf.parse("Sun Dec 15 00:00:00 CEST 2019");
		Date endDate = sdf.parse("Mon Dec 30 00:00:00 CEST 2019");
		
		pricing.setDateFrom(startDate);
		pricing.setDateTo(endDate);
		pricing.setPrice(44.4);

		
		String json = TestUtil.json(pricing);
		this.mockMvc.perform(post(URL_PREFIX + "/addPricing/1").contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDeleteService() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/deletePricing/"+5L)).andExpect(status().isOk());
	}

}
