package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
