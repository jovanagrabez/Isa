package com.example.ProjekatIsa.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.ReservationRoom;


@Service
public interface ReservationRoomService {
	
	@Transactional()
	public ReservationRoom save(ReservationRoom reservationRoom);
	
	public List<ReservationRoom> getByRoomAndInterval(Long idRoom, Date od, Date Do);
}
