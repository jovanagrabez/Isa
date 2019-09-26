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
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {
	
	@Mock
	private Car carMock;
	
	@Mock
	private CarRepository carRepositoryMock;
	
	@InjectMocks
	private CarServiceImpl carService;
	
	
	@Test
	public void testFindOneById() {
        
		when(carRepositoryMock.findOneById((long)1)).thenReturn(carMock);
        Car servis = carService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(carMock, servis);
        verify(carRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(carRepositoryMock);
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(carRepositoryMock.save(carMock)).thenReturn(carMock);
         
        Car newR = carService.addCar(carMock);
           
        assertThat(newR, is(equalTo(carMock)));
        verify(carRepositoryMock, times(1)).save(carMock);
        verifyNoMoreInteractions(carRepositoryMock);
	}
	
	
	

}
