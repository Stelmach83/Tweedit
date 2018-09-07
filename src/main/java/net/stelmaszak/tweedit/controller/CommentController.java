package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.helper.DataHelper;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String addComment(Model model, Principal principal, @PathVariable String postId) {
        User user = dataHelper.getUserSendToView(principal, model);
        Date date = new Date();
        model.addAttribute("now", date);
        List<Comment> comments = commentService.getAllComments();
        List<Post> posts = postService.getAllFromNewest();
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getUserVotesSendToView(user, model);
        dataHelper.getPostDTOandSendToView(posts, user, model);
        dataHelper.getCommentDTOandSendToView(comments, user, model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("addcomment", postId);
        model.addAttribute("appContext", "index");
        return "main";
    }

    @PostMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postComment(Model model, Principal principal, @Valid Comment comment, BindingResult result, @PathVariable String postId) {
        if (result.hasErrors()) {
            User user = dataHelper.getUserSendToView(principal, model);
            Date date = new Date();
            model.addAttribute("now", date);
            List<Post> posts = postService.getAllFromNewest();
            List<Comment> comments = commentService.getAllComments();
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getCommentDTOandSendToView(comments, user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            model.addAttribute("addcomment", postId);
            model.addAttribute("appContext", "index");
            return "main";
        } else {
            User user = dataHelper.getUserSendToView(principal, model);
            Date date = new Date();
            model.addAttribute("now", date);
            List<Post> posts = postService.getAllFromNewest();
            List<Comment> comments = commentService.getAllComments();
            commentService.saveComment(comment);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
            dataHelper.getPostDTOandSendToView(posts, user, model);
            dataHelper.getCommentDTOandSendToView(comments, user, model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            model.addAttribute("appContext", "index");
            return "main";
        }
    }

}
