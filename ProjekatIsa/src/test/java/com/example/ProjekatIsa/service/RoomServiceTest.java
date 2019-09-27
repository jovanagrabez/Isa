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
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.RoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Mock
	private Room roomMock;
	
	@Mock
	private RoomRepository roomRepositoryMock;
	
	@InjectMocks
	private RoomServiceImpl roomService;
	
	@Test
	public void testFindOneById() {
        
		when(roomRepositoryMock.findOneById((long)1)).thenReturn(roomMock);
		Room room = roomService.findOneById((long)1);
        //provjeravam da li su jednaki
		assertEquals(roomMock, room);
        verify(roomRepositoryMock, times(1)).findOneById((long)1);
        verifyNoMoreInteractions(roomRepositoryMock);
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testSave() {
		when(roomRepositoryMock.save(roomMock)).thenReturn(roomMock);
         
		Room room  = roomService.addRoom(roomMock);
           
        assertThat(room, is(equalTo(roomMock)));
        verify(roomRepositoryMock, times(1)).save(roomMock);
        verifyNoMoreInteractions(roomRepositoryMock);
	}
}
