package net.stelmaszak.tweedit.entity;

import lombok.EqualsAndHashCode;
import net.stelmaszak.tweedit.dto.CommentDTO;
import net.stelmaszak.tweedit.dto.PostDTO;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer voted;   // 2=voted up; 1=voted down; 0=no vote

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoted() {
        return voted;
    }

    public void setVoted(Integer voted) {
        this.voted = voted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public boolean isVoteForPost(PostDTO post) {
        return this.post == post.getPost();
    }

    public boolean isVoteForComment(CommentDTO comment) {
        return this.comment == comment.getComment();
    }
}
