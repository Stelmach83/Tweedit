package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import net.stelmaszak.tweedit.service.CategoryService;
import net.stelmaszak.tweedit.service.MessageService;
import net.stelmaszak.tweedit.service.PostService;
import net.stelmaszak.tweedit.service.UserService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;
    @Autowired
    private DataHelper dataHelper;

    // TODO Refactor with DataHelper

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
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("categories", categories);
        model.addAttribute("appContext", "addpost");
        return "main";
    }

    @PostMapping("/app/addpost")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postPost(Model model, Principal principal, @Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            User user = dataHelper.getUserSendToView(principal, model);
            List<Category> categories = categoryService.getCategories();
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("categories", categories);
            model.addAttribute("appContext", "addpost");
            return "main";
        } else {
            User user = dataHelper.getUserSendToView(principal, model);
            List<Category> categories = categoryService.getCategories();
            post.setCreated(new Date());
            postService.savePost(post);
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("categories", categories);
            model.addAttribute("appContext", "index");
            return "main";
        }
    }

}
