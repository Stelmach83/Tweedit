package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    List<User> getAllUsersOtherThanLoggedIn(User user);

    void saveUser(User user);

    List<User> getTop10Users();

    List<User> getTop10UserByFollowers();

    User getUserById(Long id);

    Long isUserUnique(String username);

    Long isEmailUnique(String email);

}
