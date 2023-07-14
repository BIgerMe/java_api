package web.entity.blog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_blog_info", schema = "xm", catalog = "")
public class TBlogInfo {
    private int id;
    private String title;
    private Integer userId;
    private String publish;
    private Short status;
    private Integer view;
    private String image;
    private String category;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Timestamp lastView;
    private String cover;
    private String video;
    private Integer comment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "publish")
    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    @Basic
    @Column(name = "status")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Basic
    @Column(name = "view")
    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "create_at")
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "last_view")
    public Timestamp getLastView() {
        return lastView;
    }

    public void setLastView(Timestamp lastView) {
        this.lastView = lastView;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "comment")
    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TBlogInfo tBlogInfo = (TBlogInfo) o;
        return id == tBlogInfo.id && publish == tBlogInfo.publish && Objects.equals(title, tBlogInfo.title) && Objects.equals(userId, tBlogInfo.userId) && Objects.equals(status, tBlogInfo.status) && Objects.equals(view, tBlogInfo.view) && Objects.equals(image, tBlogInfo.image) && Objects.equals(category, tBlogInfo.category) && Objects.equals(createAt, tBlogInfo.createAt) && Objects.equals(updateAt, tBlogInfo.updateAt) && Objects.equals(lastView, tBlogInfo.lastView) && Objects.equals(cover, tBlogInfo.cover) && Objects.equals(video, tBlogInfo.video) && Objects.equals(comment, tBlogInfo.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId, publish, status, view, image, category, createAt, updateAt, lastView, cover, video, comment);
    }
}
