package com.example.ProjekatIsa.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	
	
}
