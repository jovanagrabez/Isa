package com.example.ProjekatIsa.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;


@Service
public interface ReservationRoomService {
	
	public ReservationRoom save(ReservationRoom reservationRoom);
	
	public List<ReservationRoom> findAllByRoom(Room r);
	
	public List<ReservationRoom> findAllByUser(User u);
	
	public List<ReservationRoom> getByRoomAndInterval(Long idRoom, Date od, Date Do);
}
