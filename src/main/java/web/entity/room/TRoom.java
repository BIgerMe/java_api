package web.entity.room;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_room", schema = "xm", catalog = "")
public class TRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "user_id")
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoom tRoom = (TRoom) o;
        return Objects.equals(id, tRoom.id) && Objects.equals(title, tRoom.title) && Objects.equals(userId, tRoom.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId);
    }
}
