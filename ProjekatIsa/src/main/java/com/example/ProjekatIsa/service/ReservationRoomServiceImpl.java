package com.example.ProjekatIsa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
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

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ReservationRoom save(ReservationRoom reservationRoom) {
		// TODO Auto-generated method stub
		return resRepository.save(reservationRoom);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationRoom> findAllByRoom(Room r) {
		// TODO Auto-generated method stub
		return resRepository.findAllByRoom(r);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationRoom> findAllByUser(User u) {
		// TODO Auto-generated method stub
		return resRepository.findAllByUser(u);
	}

}
