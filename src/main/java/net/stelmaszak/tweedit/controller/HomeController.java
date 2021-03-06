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
    public String home(Model model, Principal principal, HttpSession session) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        if (user != null) {
            List<Post> posts = dataHelper.getPostsByFollowedCatsAndUsers(principal, model);
            if (posts.size() == 0) {
                model.addAttribute("noposts", "true");
            }
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
        }
        dataHelper.setAppContext("wall", model);
        return "main";
    }

    @RequestMapping("/app/logs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String logs(Model model, Principal principal, HttpSession session) {
        dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.getAllLogs(model);
        dataHelper.setAppContext("logs", model);
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
        Category followCat = dataHelper.getCategoryById(id);
        User user = dataHelper.getUserSendToView(principal, model);
        Set<Category> userCategories = user.getCategories();
        userCategories.add(followCat);
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

    @GetMapping("/app/followuser/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String followUser(@PathVariable Long id, Model model, Principal principal) {
        User userToFollow = dataHelper.getUserById(id);
        User user = dataHelper.getUserSendToView(principal, model);
        Set<User> following = user.getSubbedToUsers();
        if (!following.contains(userToFollow)) {
            following.add(userToFollow);
            userToFollow.setFollowers(userToFollow.getFollowers() + 1);
            dataHelper.saveUser(userToFollow);
            dataHelper.saveUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/app/unfollowuser/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String unfollowUser(@PathVariable Long id, Model model, Principal principal) {
        User userToUnfollow = dataHelper.getUserById(id);
        User user = dataHelper.getUserSendToView(principal, model);
        Set<User> following = user.getSubbedToUsers();
        if (following.contains(userToUnfollow)) {
            following.remove(userToUnfollow);
            if (userToUnfollow.getFollowers() > 0) {
                userToUnfollow.setFollowers(userToUnfollow.getFollowers() - 1);
            } else {
                userToUnfollow.setFollowers(0L);
            }
            dataHelper.saveUser(userToUnfollow);
            dataHelper.saveUser(user);
        }
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
    public String accessDenied(Principal principal, Model model) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        if (user != null) {
            dataHelper.getUserVotesSendToView(user, model);
        }
        dataHelper.getAllLogs(model);
        dataHelper.setAppContext("accessDenied", model);
        return "main";
    }
}
