package org.example.entity;

import org.example.model.Status;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "label")
public class LabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY)
    private Set<PostEntity> posts = new HashSet<>();;

    public LabelEntity(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public LabelEntity() {
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

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }
}
