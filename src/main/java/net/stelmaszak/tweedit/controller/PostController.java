package net.stelmaszak.tweedit.controller;

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

    @RequestMapping("/app/posts/{subs}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showPostsForUserSubs(@PathVariable String subs) {
        return "";
    }

    @RequestMapping("/app/posts/{category}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showPostsByCategory(@PathVariable String category) {
        return "";
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
            List<Post> posts = dataHelper.getSubPostsFromCats(principal, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.setAppContext("wall", model);
            return "main";
        }
    }

    @GetMapping("/app/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showUser(Model model, Principal principal, @PathVariable Long id) {
        User user = dataHelper.getUserSendToView(principal, model);
        User viewUser = dataHelper.getViewUser(id, model);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.setAppContext("user", model);
        return "main";
    }

}
