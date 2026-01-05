package org.example.model;

import java.util.List;

public class Post {
    private Long id;
    private String title;
    private String content;
    private Status status;
    private List<Label> labels;
    private Long writerId;

    public Post(Long id, String title, String content, Status status, List<Label> labels) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.labels = labels;
    }

    public Post(Long id, String title, String content, Status status, Long writerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.writerId = writerId;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }


    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", labels=" + labels +
                '}';
    }
}
