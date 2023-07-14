package web.entity.mongodb;

public class Chat {
    private Integer id;

    private String username;
    private String content;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

}
