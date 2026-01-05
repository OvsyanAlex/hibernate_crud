package org.example.entity;

import jakarta.persistence.*;
import org.example.model.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "writer", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class WriterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "writer_post",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<PostEntity> posts = new HashSet<>();

    public WriterEntity(Long id, String firstName, String lastName, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public WriterEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WriterEntity)) return false;
        WriterEntity that = (WriterEntity) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
