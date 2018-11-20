package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	Hotel findByName(String name);
	Page<Hotel> findAll(Pageable pageable);

}
