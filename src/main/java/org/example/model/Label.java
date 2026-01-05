package org.example.model;

public class Label {
    private Long id;
    private String name;
    private Status status;
    private Long postId;

    public Label(Long id, String name, Status status, Long postId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.postId = postId;
    }

    public Label(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Label(Long postId) {
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
