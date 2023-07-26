package web.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.room.TRoom;

@Repository
public interface RoomRepository extends JpaRepository<TRoom,Long> {

}
