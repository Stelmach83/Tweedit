package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        User newuser = new User();
        model.addAttribute("newuser", newuser);
        dataHelper.setAppContext("register", model);
        return "main";
    }

    @PostMapping("/register")
    public String postRegister(Model model, Principal principal, @Valid User newuser, BindingResult result) {
        if (result.hasErrors()) {
            dataHelper.setAppContext("register", model);
        } else {
            dataHelper.saveNewUser(newuser);
            dataHelper.setAppContext("regsuccess", model);
        }
        return "main";
    }

    @RequestMapping("/")
    public String home(Model model, Principal principal, HttpSession session) {
        User user = dataHelper.getUserSendToView(principal, model);
        if (user != null) {
            dataHelper.setTodaysDate(model);
            List<Post> posts = dataHelper.getSubPostsFromCats(principal, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
        }
        dataHelper.setAppContext("wall", model);
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
        dataHelper.setAppContext("wall", model);
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
        dataHelper.setAppContext("wall", model);
        return "main";
    }

    @GetMapping("/app/followcat/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String followCategory(@PathVariable Long id, Model model, Principal principal) {
        Category followedCat = dataHelper.getCategoryById(id);
        User user = dataHelper.getUserSendToView(principal, model);
        Set<Category> userCategories = user.getCategories();
        userCategories.add(followedCat);
        dataHelper.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/app/unfollowcat/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String unfollowCategory(@PathVariable Long id, Model model, Principal principal) {
        Category unfollowedCat = dataHelper.getCategoryById(id);
        User user = dataHelper.getUserSendToView(principal, model);
        Set<Category> userCategories = user.getCategories();
        userCategories.remove(unfollowedCat);
        dataHelper.saveUser(user);
        return "redirect:/";
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
