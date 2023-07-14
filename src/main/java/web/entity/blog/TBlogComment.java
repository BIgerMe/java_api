package web.entity.blog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_blog_comment", schema = "xm", catalog = "")
public class TBlogComment {
    private int id;
    private Integer blogId;
    private Integer userId;
    private Integer toUserId;
    private Integer baseId;
    private String content;
    private Timestamp createAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "blog_id")
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "to_user_id")
    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    @Basic
    @Column(name = "base_id")
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_at")
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
        TBlogComment that = (TBlogComment) o;
        return id == that.id && Objects.equals(blogId, that.blogId) && Objects.equals(userId, that.userId) && Objects.equals(toUserId, that.toUserId) && Objects.equals(baseId, that.baseId) && Objects.equals(content, that.content) && Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogId, userId, toUserId, baseId, content, createAt);
    }
}
