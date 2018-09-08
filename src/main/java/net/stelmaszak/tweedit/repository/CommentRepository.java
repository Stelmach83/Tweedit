package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByPostOrderByDateAsc(Post post);

    List<Comment> findAllByOrderByDateDesc();

}
