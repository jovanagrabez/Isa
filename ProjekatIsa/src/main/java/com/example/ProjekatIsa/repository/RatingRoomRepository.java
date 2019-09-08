package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;

public interface RatingRoomRepository  extends JpaRepository<RatingRoom, Long> {

	List<RatingRoom> findAllByUser(User user);
	List<RatingRoom> findAllByRoom(Room room);
}
