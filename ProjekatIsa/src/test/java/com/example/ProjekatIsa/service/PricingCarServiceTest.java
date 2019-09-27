package com.example.ProjekatIsa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.example.ProjekatIsa.model.PricingCar;
import com.example.ProjekatIsa.repository.PricingCarRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingCarServiceTest {
	
	@Mock
	private PricingCarRepository pricingRepositoryMock;
	
	@Mock
	private PricingCar pricingMock;
	
	@InjectMocks
	private PricingCarServiceImpl pricingService;
	
	
	@Test
	public void testFindAllPricingCar(){
		//	public Hotel(Long id, String name,String city, String address, String description, Double average_rating) {
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH); 
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = sdf.parse("Sun Sep 29 00:00:00 CEST 2019");
			endDate = sdf.parse("Mon Sep 30 00:00:00 CEST 2019");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<PricingCar> list1 = new ArrayList<>();
		list1.add(new PricingCar(1L, 500.0,startDate,endDate));
		list1.add(new PricingCar(2L, 500.0,startDate,endDate));
		list1.add(new PricingCar(3L, 500.0,startDate,endDate));
		list1.add(new PricingCar(4L, 500.0,startDate,endDate));
		list1.add(new PricingCar(5L, 500.0,startDate,endDate));
		list1.add(new PricingCar(6L, 500.0,startDate,endDate));
		list1.add(new PricingCar(7L, 500.0,startDate,endDate));
		list1.add(new PricingCar(8L, 500.0,startDate,endDate));
		list1.add(new PricingCar(9L, 500.0,startDate,endDate));
		list1.add(new PricingCar(10L, 500.0,startDate,endDate));
		list1.add(new PricingCar(11L, 500.0,startDate,endDate));
		list1.add(new PricingCar(12L, 500.0,startDate,endDate));
		list1.add(new PricingCar(13L, 500.0,startDate,endDate));
		list1.add(new PricingCar(14L, 500.0,startDate,endDate));
		list1.add(new PricingCar(15L, 500.0,startDate,endDate));
		
		when(pricingRepositoryMock.findAll()).thenReturn(list1);
		List<PricingCar> pricingList = pricingService.findAll();
		assertThat(pricingList).hasSize(15);
		
		//provera da je metoda findall pozvana samo jednom
		verify(pricingRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(pricingRepositoryMock);
	}
	
	@Test
	public void testFindOneById() {
        
		when(pricingRepositoryMock.findOneById((long)1)).thenReturn(pricingMock);
		PricingCar pricing = pricingService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(pricingMock, pricing);
        verify(pricingRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(pricingRepositoryMock);
	}
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(pricingRepositoryMock.save(pricingMock)).thenReturn(pricingMock);
         
		PricingCar pricing  = pricingService.save(pricingMock);
           
        assertThat(pricing, is(equalTo(pricingMock)));
        verify(pricingRepositoryMock, times(1)).save(pricingMock);
        verifyNoMoreInteractions(pricingRepositoryMock);
	}

}
