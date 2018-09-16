package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllByCategoryInOrderByCreatedDesc(Set<Category> categories);

}
