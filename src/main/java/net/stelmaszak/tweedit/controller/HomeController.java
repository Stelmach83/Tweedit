package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "customlogin";
    }


    @RequestMapping("/app")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
