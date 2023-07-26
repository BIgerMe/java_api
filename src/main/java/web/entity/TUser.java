package web.entity;

import com.google.gson.JsonElement;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_user", schema = "xm", catalog = "")
public class TUser {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String token;
    private String tokenExpire;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return id == tUser.id && Objects.equals(username, tUser.username) && Objects.equals(password, tUser.password) && Objects.equals(nickname, tUser.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, nickname);
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "token_expire")
    public String getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }
}
