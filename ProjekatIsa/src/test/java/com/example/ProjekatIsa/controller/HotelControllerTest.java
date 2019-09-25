package com.example.ProjekatIsa.controller;
import static org.assertj.core.api.Assertions.assertThat;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormHotel;


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
		.andExpect(jsonPath("$.[*].city").value(hasItem("Novi Sad")))
		.andExpect(jsonPath("$.[*].address").value(hasItem("Trg slobode 2, Novi Sad, Srbija")))
		.andExpect(jsonPath("$.[*].description").value(hasItem("U samom centru grada. Stara arhitektura")))
		.andExpect(jsonPath("$.[*].average_rating").value(hasItem(4.3)));
	}
	
	@Transactional
	@Rollback(true)
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
	
	@Transactional
	@Rollback(true)
	@Test
	public void testChangeHotel() throws Exception {
		HotelDTO hotel = new HotelDTO();

		hotel.setCity("Novi Sad");
		hotel.setAddress("Stevana Musica 11,Novi Sad,Srbija");
		hotel.setName("Novi Hotel");
		hotel.setDescription("opis");
		hotel.setAverage_rating(2.2);

		String json = TestUtil.json(hotel);
		this.mockMvc.perform(post(URL_PREFIX + "/changeHotel/1").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDeleteHotel() throws Exception {

		Long idHotel = (long)3 ;
		String json = TestUtil.json(idHotel);
		this.mockMvc.perform(post(URL_PREFIX + "/deleteHotel").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testSearchHotel() throws Exception{
		SearchFormHotel newSF = new SearchFormHotel();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   startDate       = format.parse ( "2019-09-28" );
		Date   endDate       = format.parse ( "2009-09-29" );

		newSF.setCity("Novi Sad");
		newSF.setStartDate(startDate);
		newSF.setEndDate(endDate);
		newSF.setName("Vojvodina");
		
		String json = TestUtil.json(newSF);
		this.mockMvc.perform(post(URL_PREFIX + "/searchHotels").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testGetLastWeekReservations() throws Exception{
		
		//za hotel gdje je id = 1 i poslenju sedmicu
		//ocekujem 13 rezervaciju
		this.mockMvc.perform(get(URL_PREFIX + "/getLastWeekReservations/1/2019-09-25" )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(13)));
	}
	
	@Test
	public void testGetAllReservations() throws Exception{
		
		//za hotel gdje je id = 1 ocekujem iduce rezervacije
		this.mockMvc.perform(get(URL_PREFIX + "/getAllReservations/1" )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	
	@Test
	public void testGetAllRatingsHotel() throws Exception{
		
		//za hotel gdje je id = 1 ocekujem iduce ocjene
		this.mockMvc.perform(get(URL_PREFIX + "/getAllRatingsHotel/1" )).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
	}

	// METODA KOJA VRACA PRIHODe ZA IZABRANI PERIOD
	//izabrala sam prihod od 19 - 20 septebra sto je nula
	@Test
		public void testGetRevenuesRent() throws Exception {
		MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/getHotelRevenue/1/2019-09-19 00:00:00.0/2019-09-20 00:00:00.0")).andExpect(status().isOk())
		.andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		double retVal = Double.parseDouble(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
		    .isEqualTo(0.00);
	}
	@Test
	public void testCountAverageRating() throws Exception {
		MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/countAverageRating/1")).andExpect(status().isOk())
		.andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		double retVal = Double.parseDouble(resultAsString);
		assertThat(retVal)
		    .isEqualTo(4.00);
	}
}
