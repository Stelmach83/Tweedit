package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String addComment(Model model, Principal principal, @PathVariable String postId) {
        User user = dataHelper.getUserSendToView(principal, model);
        List<Post> posts = dataHelper.getAllPostsFromNewest();
        dataHelper.setTodaysDate(model);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getUserVotesSendToView(user, model);
        dataHelper.getPostDTOandSendToView(posts, user, model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.setAppContext("wall", model);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("addcomment", postId);
        return "main";
    }

    @PostMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postComment(Model model, Principal principal, @Valid Comment comment, BindingResult result, @PathVariable String postId) {
        if (result.hasErrors()) {
            User user = dataHelper.getUserSendToView(principal, model);
            List<Post> posts = dataHelper.getAllPostsFromNewest();
            dataHelper.setTodaysDate(model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            dataHelper.setAppContext("wall", model);
            model.addAttribute("addcomment", postId);
            return "main";
        } else {
            User user = dataHelper.getUserSendToView(principal, model);
            dataHelper.setTodaysDate(model);
            List<Post> posts = dataHelper.getAllPostsFromNewest();
            dataHelper.saveCommment(comment, user);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            dataHelper.setAppContext("wall", model);
            return "main";
        }
    }

}
