package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

}
