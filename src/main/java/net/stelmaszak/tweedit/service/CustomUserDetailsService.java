package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.CustomUserDetails;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
