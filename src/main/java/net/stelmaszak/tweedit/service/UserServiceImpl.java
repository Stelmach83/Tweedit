package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersOtherThanLoggedIn(User user) {
        List<User> result = getAllUsers().stream()
                .filter(u -> !u.equals(user))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
