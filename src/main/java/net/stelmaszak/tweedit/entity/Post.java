package net.stelmaszak.tweedit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date created;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    private Content content;

    @OneToMany
    private Set<Comment> comments;

    @OneToMany
    private Set<Vote> votes;

    private Long points;


    @PrePersist
    public void prePersist() {
        if (points == null) {
            points = 0l;
        }
    }

}
