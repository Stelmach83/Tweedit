package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private DataHelper dataHelper;

    @RequestMapping("/app/userposts/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showPostsForUserSubs(@PathVariable Long userId) {
        return "";
    }

    @RequestMapping("/app/posts/{catId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showPostsByCategory(@PathVariable Long catId, Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        Category category = dataHelper.getCategoryById(catId);
        List<Post> posts = dataHelper.getPostsByCategory(category);
        model.addAttribute("catview", category);
        dataHelper.getPostDTOandSendToView(posts, user, model);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.setAppContext("wall", model);
        dataHelper.setTodaysDate(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        return "main";
    }

    @GetMapping("/app/addpost")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String addPost(Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        Post post = new Post();
        model.addAttribute("post", post);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.setAppContext("addpost", model);
        return "main";
    }

    @PostMapping("/app/addpost")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postPost(Model model, Principal principal, @Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            User user = dataHelper.getUserSendToView(principal, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            dataHelper.setAppContext("addpost", model);
            return "main";
        } else {
            User user = dataHelper.getUserSendToView(principal, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.setCreatedAndUserDateAndSavePost(post, user);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            List<Post> posts = dataHelper.getPostsByFollowedCatsAndUsers(principal, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.setAppContext("wall", model);
            return "main";
        }
    }
}
