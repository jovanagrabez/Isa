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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.RentalCarRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarServiceTest {
	
	@Mock
	private RentalCarRepository rentacarRepositoryMock;
	
	@Mock
	private RentACar rentacarMock;
	
	@InjectMocks
	private RentalCarServiceImpl rentacarService;
	
	
	
	@Test
	public void testFindOneById() {
        
		when(rentacarRepositoryMock.findOneById((long)1)).thenReturn(rentacarMock);
        RentACar servis = rentacarService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(rentacarMock, servis);
        verify(rentacarRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(rentacarRepositoryMock);
	}
	
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(rentacarRepositoryMock.save(rentacarMock)).thenReturn(rentacarMock);
         
        RentACar newR = rentacarService.addService(rentacarMock);
           
        assertThat(newR, is(equalTo(rentacarMock)));
        verify(rentacarRepositoryMock, times(1)).save(rentacarMock);
        verifyNoMoreInteractions(rentacarRepositoryMock);
	}
	
	@Test
	public void testGetRentalCars(){
		
		ArrayList<RentACar> list = new ArrayList<>();
		list.add(new RentACar(1L,"CarFlexi","Beograd","Dunavska 22, Beograd, Srbija","Servis broj 1.",4.2));
		list.add(new RentACar(1L,"EasyRentCars","Beograd","Knez Mihajlova 45, Beograd, Srbija","Najveci izbor vozila",3.9));
		list.add(new RentACar(1L,"EuropeCar","Beograd","Cara Dusana 10, Beograd, Srbija","Brzo do zeljenih vozila",4.8));
		list.add(new RentACar(1L,"Inex Rent A Car","Novi Sad","Bulevar Evrope 2, Novi Sad, Srbija","Najpovoljnije usluge",4.6));
		list.add(new RentACar(1L,"Max Rent A Car","Novi Sad","Bulevar Evrope 10, Novi Sad, Srbija","luux",3.3));
		list.add(new RentACar(1L,"Lux","London","London,Velika Britanija","luux",3.3));
		
		when(rentacarRepositoryMock.findAll()).thenReturn(list);
		
		List<RentACar> servisi = rentacarService.getAll();		
		assertThat(servisi).hasSize(6);
		System.out.println("Niiz"  + servisi.size());
		verify(rentacarRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(rentacarRepositoryMock);

		
	}
	

}
