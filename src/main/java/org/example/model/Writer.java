package org.example.model;


import java.util.List;

public class Writer {
    private Long id;
    private String firstName;
    private String lastName;
    private Status status;
    private List<Post> posts;

    public Writer(Long id, String firstName, String lastName, Status status, List<Post> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.posts = posts;
    }

    public Writer(Long id, String firstName, String lastName, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", posts=" + posts +
                '}';
    }
}
