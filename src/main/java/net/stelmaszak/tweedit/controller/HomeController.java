package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private DataHelper dataHelper;


    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        if (user != null) {
            dataHelper.setTodaysDate(model);
            List<Post> posts = dataHelper.getAllPostsFromNewest();
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
        }
        dataHelper.setAppContext("index", model);
        return "main";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal, Model model) {
        if (error != null) {
            return "redirect:error";
        }
        if (logout != null) {
            return "redirect:lout";
        }
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.setAppContext("index", model);
        return "main";
    }

    @RequestMapping("/lout")
    public String logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
        try {
            request.logout();
            SecurityContextHolder.clearContext();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.setAppContext("index", model);
        return "main";
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

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
