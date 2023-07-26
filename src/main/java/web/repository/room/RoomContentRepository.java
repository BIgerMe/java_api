package web.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.room.TRoomContent;

import java.util.ArrayList;

@Repository
public interface RoomContentRepository extends JpaRepository<TRoomContent,Long> {
    ArrayList<TRoomContent> getAllByRoomIdOrderByCreateAtAsc(int room_id);
}
