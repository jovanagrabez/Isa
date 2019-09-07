package com.example.ProjekatIsa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;

@Service
public class ReservationRoomServiceImpl implements ReservationRoomService {

	@Autowired
	ReservationRoomRepository resRepository;
	
	@Override
	public List<ReservationRoom> getByRoomAndInterval(Long idRoom, Date od, Date Do) {
		// TODO Auto-generated method stub
		return resRepository.findAllForInterval(idRoom, od, Do);
	}

}
