package model;

import java.util.Objects;

public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    /**
     * No args constructor for use in serialization
     */
    public Post() {
    }

    /**
     * @param id
     * @param title
     * @param body
     * @param userId
     */
    public Post(Integer userId, Integer id, String title, String body) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("userId: %d, id: %d, title: %s, body: %s", userId, id, title, body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getUserId().equals(post.getUserId()) && getId().equals(post.getId()) && getTitle().equals(post.getTitle()) && getBody().equals(post.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getId(), getTitle(), getBody());
    }
}