package web.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@Component
@ConfigurationProperties(prefix = "test")
public class TTestEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "test")
    private String test;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTestEntity that = (TTestEntity) o;
        return id == that.id && Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, test);
    }
}
