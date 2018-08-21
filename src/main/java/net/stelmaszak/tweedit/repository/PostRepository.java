package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
