package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    List<User> getAllByOrderByPointsDesc();

    List<User> getAllByOrderByFollowersDesc();

    Long countUsersByUsername(String username);

    Long countUsersByEmail(String email);

}
