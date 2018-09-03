package net.stelmaszak.tweedit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CommentController {

    @GetMapping("/app/addcomment")
    public String addComment(Model model, Principal principal) {


        model.addAttribute("appContext", "");
        return "main";
    }
}
