package net.stelmaszak.tweedit.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = 3, max = 15, message = "Username needs to be between 3 - 15 characters.")
    private String username;

    @Size(min = 4, max = 200, message = "Password needs to be between 4 - 200 characters.")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany
    private Set<Post> posts;                            // user ma set swoich postów

    @OneToMany
    private Set<Comment> comments;                      // user ma set swoich commentów

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_category", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;                   // user ma subskrypcje do categorii (many to many)

    @OneToMany
    private Set<Vote> votes;                            // user ma set swoich łapek w górę i dół

    @OneToMany
    private Set<Message> received;                      // user ma set otrzymanych wiadomości

    @OneToMany
    private Set<Message> sent;                          // user ma set wysłanych wiadomości

    @ColumnDefault("0")
    private Long points;                                // zsumowana ilość puntów za Votes z Comments i Posts

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    @PrePersist
//    public void prePersist() {
//        if (points == null) {
//            points = 0l;
//        }
//    }
}
