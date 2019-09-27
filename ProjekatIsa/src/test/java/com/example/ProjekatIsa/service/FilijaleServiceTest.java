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

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilijaleServiceTest {
	
	@Mock
	private Filijale filMock;
	
	@Mock
	private FilijaleRepository filRepositoryMock;
	
	@InjectMocks
	private FilijaleServiceImpl filService;
	
	@Mock
	private RentalCarRepository rentRepositoryMock;
	
	@Test
	public void testFindOneById() {
        
		when(filRepositoryMock.findOneById((long)1)).thenReturn(filMock);
        Filijale servis = filService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(filMock, servis);
        verify(filRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(filRepositoryMock);
	}
	
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(filRepositoryMock.save(filMock)).thenReturn(filMock);
         
        Filijale newR = filService.addFilijale(filMock);
           
        assertThat(newR, is(equalTo(filMock)));
        verify(filRepositoryMock, times(1)).save(filMock);
        verifyNoMoreInteractions(filRepositoryMock);
	}
	
	
	@Test
	public void testGetFilijale(){
		
		ArrayList<Filijale> list = new ArrayList<>();
		RentACar service = new RentACar();
		service = rentRepositoryMock.findOneById((long) 1);
		list.add(new Filijale(1L,"Srbija","Beograd","Tekelijina 53, Beograd, Srbija",service));
		list.add(new Filijale(1L,"Velika Britanija","London","London",service));
		list.add(new Filijale(1L,"Srbija","Novi Sad","Bulevar Evrope 2, Novi Sad, Srbija",service));
		list.add(new Filijale(1L,"Srbija","Beograd","Knez Mihajlova 45, Beograd, Srbija",service));
		list.add(new Filijale(1L,"Srbija","Beograd","Knez Mihajlova 55, Beograd, Srbija",service));
		list.add(new Filijale(1L,"Srbija","Beograd","Zeleni venac 5, Beograd, Srbija",service));
		list.add(new Filijale(1L,"Srbija","Novi Sad","aa",service));
		list.add(new Filijale(1L,"Srbija","Beograd","a",service));
		list.add(new Filijale(1L,"Srbija","Novi Sad","a",service));
		list.add(new Filijale(1L,"Srbija","Beograd","a",service));
		list.add(new Filijale(1L,"Srbija","Beograd","a",service));

		
		
		when(filRepositoryMock.findAll()).thenReturn(list);
		
		List<Filijale> servisi = filService.getAll();		
		assertThat(servisi).hasSize(11);
		System.out.println("Niiz"  + servisi.size());
		verify(filRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(filRepositoryMock);

		
	}
	
	
	
	
	
	

}
