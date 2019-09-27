package com.example.ProjekatIsa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.RatingCarRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingCarServiceTest {
	
	@Mock
	private RatingCar ratingCarMock;
	
	@Mock 
	private RatingCarRepository ratingRepositoryMock;
	
	@InjectMocks
	private RatingCarService ratingService;
	
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(ratingRepositoryMock.save(ratingCarMock)).thenReturn(ratingCarMock);
         
        RatingCar newR = ratingService.save(ratingCarMock);
           
        assertThat(newR, is(equalTo(ratingCarMock)));
        verify(ratingRepositoryMock, times(1)).save(ratingCarMock);
        verifyNoMoreInteractions(ratingRepositoryMock);
	}
	


}
