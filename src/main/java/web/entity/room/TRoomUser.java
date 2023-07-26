package web.entity.room;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_room_user", schema = "xm", catalog = "")
public class TRoomUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "room_id")
    private Integer roomId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "create_at")
    private Timestamp createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoomUser tRoomUser = (TRoomUser) o;
        return Objects.equals(id, tRoomUser.id) && Objects.equals(roomId, tRoomUser.roomId) && Objects.equals(userId, tRoomUser.userId) && Objects.equals(createAt, tRoomUser.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, userId, createAt);
    }
}
