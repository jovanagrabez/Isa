package com.example.ProjekatIsa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.User;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
	CarReservation save(CarReservation c);
	List<CarReservation> findAllByCar(Car cr);
	List<CarReservation> findAllByUser(User user);
	@Query(value = "SELECT * FROM car_reservation c WHERE car_id=?1 and start_date >= ?2 and start_date<=?3", nativeQuery=true)
	List<CarReservation> findAllForInterval(Long idCar, Date od, Date Do);

	

}
