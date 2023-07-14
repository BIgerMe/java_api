package web.entity.blog;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_blog_content", schema = "xm", catalog = "")
public class TBlogContent {
    private int id;
    private Integer blogId;
    private String content;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TBlogContent that = (TBlogContent) o;
        return id == that.id && Objects.equals(blogId, that.blogId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogId, content);
    }
}
