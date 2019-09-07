package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {
	List<ReservationRoom> findAllByRoom(Room room);
	ReservationRoom save(ReservationRoom rr);
	List<ReservationRoom> findAllByUser(User user);
}
