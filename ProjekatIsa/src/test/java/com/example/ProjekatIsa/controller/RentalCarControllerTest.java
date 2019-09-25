package com.example.ProjekatIsa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
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
import java.util.List;

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

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.SearchFormHotel;
import com.example.ProjekatIsa.model.SearchFormServices;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class RentalCarControllerTest {
	
	private static final String URL_PREFIX = "/rentalcars";
	
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
	public void testDodajRentACar() throws Exception {
		RentACar newCar = new RentACar();
		
		newCar.setName("RentServis");
		newCar.setAdress("Dimitrija Avramovica 5,Novi Sad, Srbija");
		newCar.setCity("Novi Sad");
		newCar.setAverage_rating(3.2);
		newCar.setDescription("Najbolji servis");
		
		String json = TestUtil.json(newCar);
		this.mockMvc.perform(post(URL_PREFIX + "/addService" ).contentType(contentType).content(json)).andExpect(status().is2xxSuccessful());

		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniRentACar() throws Exception {
		RentACar service = new RentACar();
		
		List<Car> car = new ArrayList<Car>();
		List<Filijale> fil = new ArrayList<Filijale>();
		
		service.setCar(car);
		service.setFilijale(fil);
		service.setAdress("Futoska 6, Novi Sad, Srbija");
		service.setAverage_rating(3.6);
		service.setCity("Novi Sad");
		service.setDescription("novi opis");
		service.setName("EasyRentCars");
		service.setId((long) 2);
		
		String json = TestUtil.json(service);
		this.mockMvc.perform(post(URL_PREFIX + "/changeService/"+2L).contentType(contentType).content(json)).andExpect(status().isOk());
		
		
	}
	
	
	@Test
	public void testGetServis() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getAll" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("CarFlexi")))
		.andExpect(jsonPath("$.[*].adress").value(hasItem("Dunavska 22, Beograd, Srbija")))
		.andExpect(jsonPath("$.[*].description").value(hasItem("Servis broj 1.")))
		.andExpect(jsonPath("$.[*].average_rating").value(hasItem(4.2)));
		
	}
	
	@Test
	public void testAddFilijale() throws Exception{
		RentACar servis = new RentACar();
		Filijale fil = new Filijale();
		fil.setAdresa("Cirpanova 5");
		fil.setDrzava("Srbija");
		fil.setGrad("Novi Sad");
		fil.setRentACar(servis);
		String json = TestUtil.json(fil);
		this.mockMvc.perform(post(URL_PREFIX + "/addFil/" + 1L).contentType(contentType).content(json)).andExpect(status().isOk());
		
		
	}
	
	
	@Test
	public void testGetAllCars() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getAllCars/" +1L )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("BMW")))
		.andExpect(jsonPath("$.[*].car_number").value(hasItem("NS-0786")))
		.andExpect(jsonPath("$.[*].price").value(hasItem(500)))
		.andExpect(jsonPath("$.[*].average_rating").value(hasItem(4.1)))
		.andExpect(jsonPath("$.[*].prod_year").value(hasItem(2011)));
		
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testIzbrisiRentACar() throws Exception {
		Long idRent = (long)3;
		String json = TestUtil.json(idRent);
		this.mockMvc.perform(post(URL_PREFIX + "/deleteService").contentType(contentType).content(json)).andExpect(status().isOk());


	}
	
	@Test
	public void testSearchService() throws Exception{
		SearchFormServices newSF = new SearchFormServices();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   startDate       = format.parse ( "2019-09-28" );
		Date   endDate       = format.parse ( "2009-09-29" );

		newSF.setCity("Novi Sad");
		newSF.setStartDate(startDate);
		newSF.setEndDate(endDate);
		newSF.setName("CarFlexi");
		
		String json = TestUtil.json(newSF);
		this.mockMvc.perform(post(URL_PREFIX + "/searchService").contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetAllReservations() throws Exception{
		
		//za hotel gdje je id = 1 ocekujem iduce rezervacije
		this.mockMvc.perform(get(URL_PREFIX + "/getAllReservations/"+1L  )).andExpect(status().isOk());
	}
	
	
//	@Test
//	public void testGetAllRatingsService() throws Exception{
//		
//		//za hotel gdje je id = 1 ocekujem iduce ocjene
//		this.mockMvc.perform(get(URL_PREFIX + "/getAllRatingsService/"+1L )).andExpect(status().isOk())
//		.andExpect(content().contentType(contentType))
//		.andExpect(jsonPath("$.[*].rate").value(hasItem(5)));
//	}
	
	
	// METODA KOJA VRACA PRIHODe ZA IZABRANI PERIOD
		//izabrala sam prihod od 19 - 20 septebra sto je nula
		@Test
			public void testGetRevenuesRent() throws Exception {
			MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/getServiceRevenue/1/2019-09-19 00:00:00.0/2019-09-20 00:00:00.0")).andExpect(status().isOk())
			.andReturn();
			String resultAsString = result.getResponse().getContentAsString();
			double retVal = Double.parseDouble(resultAsString);
			//System.out.println(retVal);
			assertThat(retVal)
			    .isEqualTo(0.00);
		}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	


}
