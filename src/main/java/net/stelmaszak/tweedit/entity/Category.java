package net.stelmaszak.tweedit.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Post> posts;

    @ManyToMany(mappedBy = "categories")
    private Set<User> subbedUsers;  //many to many

    public Category() {
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getSubbedUsers() {
        return subbedUsers;
    }

    public void setSubbedUsers(Set<User> subbedUsers) {
        this.subbedUsers = subbedUsers;
    }
}
