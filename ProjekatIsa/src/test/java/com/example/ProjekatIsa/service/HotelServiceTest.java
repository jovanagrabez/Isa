package com.example.ProjekatIsa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.HotelRepository;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {
	@Mock
	private HotelRepository hotelRepositoryMock;
	
	@Mock
	private Hotel hotelMock;
	
	@InjectMocks
	private HotelServiceImpl hotelService;
	
	@Test
	public void testGetHotels(){
		//	public Hotel(Long id, String name,String city, String address, String description, Double average_rating) {

		ArrayList<Hotel> list1 = new ArrayList<>();
		list1.add(new Hotel(1L,"Vojvodina","Novi Sad", "Trg slobode 2, Novi Sad, Srbija","U samom centru grada. Stara arhitektura",4.3));
		list1.add(new Hotel(2L,"Grand hotel","Beograd","Bulevar Nikole Tesle 3, Beograd, Srbija","Lorem ipsum dolor sit amet, pri ei duis natum.",4.9));
		list1.add(new Hotel(1L,"Butique","Beograd","Trg Republike 3, Beograd, Srbija","Pro eu dolore vivendo ponderum.",4.3));
		list1.add(new Hotel(1L,"Prezident","Novi Sad","Futoška 109, Novi Sad, Srbija"," Eos ad oblique adolescens moderatius.",3.9));
		list1.add(new Hotel(1L,"Biser","Derventa","Kralja Petra I, Derventa, Bosna i Hercegovina ","Cu sit sint ignota, sit id scaevola.",4.5));

		when(hotelRepositoryMock.findAll()).thenReturn(list1);
		List<Hotel> hoteli = hotelService.getAll();
		assertThat(hoteli).hasSize(5);
		
		//provera da je metoda findall pozvana samo jednom
		verify(hotelRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(hotelRepositoryMock);
		
	}
	
	@Test
	public void testFindOneById() {
        
		when(hotelRepositoryMock.findOneById((long)1)).thenReturn(hotelMock);
        Hotel hotel = hotelService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(hotelMock, hotel);
        verify(hotelRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(hotelRepositoryMock);
	}
	
	@Test
	public void testFindAllByCity() {
        
		List<Hotel> list1 = new ArrayList<>();
		list1.add(new Hotel(1L,"Vojvodina","Novi Sad", "Trg slobode 2, Novi Sad, Srbija","U samom centru grada. Stara arhitektura",4.3));
		list1.add(new Hotel(1L,"Prezident","Novi Sad","Futoška 109, Novi Sad, Srbija"," Eos ad oblique adolescens moderatius.",3.9));

		

		when(hotelRepositoryMock.findAllByCity("Novi Sad")).thenReturn(list1);
		List<Hotel> hoteli = hotelService.findAllByCity("Novi Sad");
		assertThat(hoteli).hasSize(2);
		
		//provera da je metoda findall pozvana samo jednom
		verify(hotelRepositoryMock, times(1)).findAllByCity("Novi Sad");
        verifyNoMoreInteractions(hotelRepositoryMock);
	}
	
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);
         
        Hotel savedHotel = hotelService.addHotel(hotelMock);
           
        assertThat(savedHotel, is(equalTo(hotelMock)));
        verify(hotelRepositoryMock, times(1)).save(hotelMock);
        verifyNoMoreInteractions(hotelRepositoryMock);
	}

	
	
}
