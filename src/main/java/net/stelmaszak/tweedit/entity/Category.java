package net.stelmaszak.tweedit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Post> posts;

    @ManyToMany(mappedBy = "categories")
    private Set<User> users;  //many to many

}
