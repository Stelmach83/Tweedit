package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Role;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/home")
    public String home() {

        User user1 = new User();
        user1.setUsername("Stelmach");
        user1.setPassword("michal");
        user1.setEmail("stelmaszak@gmail.com");
        userRepository.saveAndFlush(user1);


        User user2 = new User();
        user2.setUsername("Pleb");
        user2.setPassword("michal");
        user2.setEmail("michal@gmail.com");
        userRepository.saveAndFlush(user2);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(Role.ADMIN);
        roles1.add(Role.USER);
        user1.setRoles(roles1);
        userRepository.saveAndFlush(user1);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(Role.USER);
        user2.setRoles(roles2);
        userRepository.saveAndFlush(user2);


        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "customlogin";
    }


    @RequestMapping("/app")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public String test() {
        return "logged in";
    }

    @GetMapping("/all")
    @ResponseBody
    public String publicView() {
        return "everyone can see this.";
    }
}
