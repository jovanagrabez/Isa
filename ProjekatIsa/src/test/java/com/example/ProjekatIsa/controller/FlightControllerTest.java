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
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
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

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.model.SeatArrangement;
import com.example.ProjekatIsa.repository.DestinationRepository;
import com.example.ProjekatIsa.repository.SeatRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class FlightControllerTest {
	
	
private static final String URL_PREFIX = "/flight";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private SeatRepository seatRep;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();	
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
//	@WithUserDetails("hoteladmin@gmail.com")
	public void testDodajLet() throws Exception {
		Flight let = new Flight();
		Set<Destination> dest = new HashSet<Destination>();
		Set< Seat> seat = new HashSet<Seat>();
		SeatArrangement ar= new SeatArrangement();
		let.setAverageRating(0);
		let.setBaggageDescription("nema");
		let.setBusinessPrice(500);
		let.setDistance(45);
	//	let.setEconomyPrice(100);
		let.setFirstPrice(455);
		let.setLanding(new Date(2019,9,9));
		let.setTake_off(new Date(2019,9,9));
		let.setNumber(5);
		let.setNumberOfRating(5);
		let.setPremiumEconomyPrice(2.3);
		let.setDestination(dest);
		let.setSeats(seat);
		let.setSeatArrangement(ar);
		
	

		String json = TestUtil.json(let);
		this.mockMvc.perform(post(URL_PREFIX ).contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	
	@Test
	 public void obrisiLet() throws Exception{
	     mockMvc.perform(delete(URL_PREFIX +"/"+(long) 1 + "/" + (long)1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	
	@Test
	 public void obrisiLetFail() throws Exception{
	     mockMvc.perform(delete(URL_PREFIX +"/"+(long) 50 + "/" + (long)1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void rezervacija() throws Exception {
		FlightReservation f = new FlightReservation();
		Passenger p = new Passenger();
		Seat s= this.seatRep.findSeatById((long)1);
		p.setPassengerId((long) 1);
		p.setPassengerLastName("lalal");
		p.setPassengerName("jdskj");
		p.setPassengerPassport("12345");
		p.setSeat(s);
		
		Set<Passenger> pas =  new HashSet<Passenger>();
		pas.add(p);
		f.setUserId((long) 1);
		f.setFlightId((long) 5 );
		f.setDatum(new Date());
        f.setPassengersOnSeats(pas);
		

		

		String json = TestUtil.json(f);
		this.mockMvc.perform(post(URL_PREFIX + "/reservations").contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
//	@WithUserDetails("avioadmin@gmail.com")
	public void rezervacijaFail() throws Exception {
		FlightReservation f = new FlightReservation();
		
		
		Set<Passenger> pas =  new HashSet<Passenger>();
		f.setUserId((long) 1);
		f.setFlightId((long) 5 );
		f.setDatum(new Date());
 //       f.setPassengersOnSeats(pas);
		

		

		String json = TestUtil.json(f);
		this.mockMvc.perform(post(URL_PREFIX + "/reservations").contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	 public void pozivnica() throws Exception{
	     mockMvc.perform(get(URL_PREFIX +"/invite" + "/" + (long)10)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().is4xxClientError());
	 }
	
	
	@Test
	 public void pozivnicaDeleteFail() throws Exception{
	     mockMvc.perform(delete(URL_PREFIX +"/invite" + "/" + (long)10)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isBadRequest());
	 }
	
	
	@Test
	 public void prijatelji() throws Exception{
	     mockMvc.perform(get("/friends/isasaracelik@gmail.com")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	
	
	@Test
	 public void prijateljiFail() throws Exception{
	     mockMvc.perform(get("/friends/lalala")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().is2xxSuccessful());
	 }
	
	
	
	
	
	
	

}
