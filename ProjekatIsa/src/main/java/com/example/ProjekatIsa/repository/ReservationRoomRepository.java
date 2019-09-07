package com.example.ProjekatIsa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {
	List<ReservationRoom> findAllByRoom(Room room);
	ReservationRoom save(ReservationRoom rr);
	List<ReservationRoom> findAllByUser(User user);
	@Query(value = "SELECT * FROM reservation_room r WHERE room_id=?1 and start_date >= ?2 and start_date<=?3", nativeQuery=true)
	List<ReservationRoom> findAllForInterval(Long idRoom, Date od, Date Do);
}
