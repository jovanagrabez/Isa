package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.RatingRoom;

public interface RatingRoomRepository  extends JpaRepository<RatingRoom, Long> {

}
