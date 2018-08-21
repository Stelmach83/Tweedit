package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
