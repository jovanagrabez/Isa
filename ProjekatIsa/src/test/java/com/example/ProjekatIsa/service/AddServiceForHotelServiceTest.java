package com.example.ProjekatIsa.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.AdditionalServiceForHotelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddServiceForHotelServiceTest {
	@Mock
	private AdditionalServiceForHotelRepository addRepositoryMock;
	
	@Mock
	private AdditionalServiceForHotel addMock;
	
	@InjectMocks
	private AdditionalServiceForHotelServiceImpl addService;
	
	@Test
	public void testFindOneById() {
        
		when(addRepositoryMock.findOneById((long)1)).thenReturn(addMock);
		AdditionalServiceForHotel add = addService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(addMock, add);
        verify(addRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(addRepositoryMock);
	}
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(addRepositoryMock.save(addMock)).thenReturn(addMock);
         
		AdditionalServiceForHotel add  = addService.addService(addMock);
           
        assertThat(add, is(equalTo(addMock)));
        verify(addRepositoryMock, times(1)).save(addMock);
        verifyNoMoreInteractions(addRepositoryMock);
	}
}
