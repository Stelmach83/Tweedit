package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByUser(User user);

}
