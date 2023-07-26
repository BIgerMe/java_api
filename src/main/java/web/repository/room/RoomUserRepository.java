package web.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.entity.room.TRoomUser;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface RoomUserRepository extends JpaRepository<TRoomUser,Long> {
    @Query(value = "SELECT t0.*,t1.title FROM t_room_user t0 left join t_room t1 on t0.room_id = t1.id where t0.user_id = :user_id",nativeQuery = true)
    ArrayList<Map<String,Object>> getRoomByUserID(int user_id);
}
