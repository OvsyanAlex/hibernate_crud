package org.example.entity;
import org.example.model.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "content"})})
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_label",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<LabelEntity> labels = new HashSet<>();

    public PostEntity(Long id, String title, String content, Status status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public PostEntity() {
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

    public Set<LabelEntity> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelEntity> labels) {
        this.labels = labels;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostEntity)) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(title, that.title) &&
               Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }
}
