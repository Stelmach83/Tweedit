package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.helper.DataHelper;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class StatelessController {

    @Autowired
    private PostService postService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DataHelper dataHelper;

    @PostMapping("/app/votedup/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteUp(@PathVariable Long id, Model model, Principal principal) {
        Post votedPost = postService.getPostById(id);
        Long postPoints = votedPost.getPoints() + 1;
        User owner = votedPost.getUser();
        Long userPoints = owner.getPoints() + 1;
        owner.setPoints(userPoints);
        votedPost.setPoints(postPoints);
        User votingUser = dataHelper.getUserSendToView(principal, model);
        Vote newVote = new Vote();
        newVote.setUser(votingUser);
        newVote.setPost(votedPost);
        newVote.setVoted(2);
        postService.savePost(votedPost);
        voteService.saveVote(newVote);
        userService.saveUser(owner);
    }

    @PostMapping("/app/voteddown/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteDown(@PathVariable Long id, Model model, Principal principal) {
        Post votedPost = postService.getPostById(id);
        Long postPoints = votedPost.getPoints() - 1;
        User owner = votedPost.getUser();
        Long userPoints = owner.getPoints() - 1;
        owner.setPoints(userPoints);
        votedPost.setPoints(postPoints);
        User votingUser = dataHelper.getUserSendToView(principal, model);
        Vote newVote = new Vote();
        newVote.setUser(votingUser);
        newVote.setPost(votedPost);
        newVote.setVoted(1);
        postService.savePost(votedPost);
        voteService.saveVote(newVote);
        userService.saveUser(owner);
    }

    @PostMapping("/app/votedupcomment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteUpComment(@PathVariable Long id, Model model, Principal principal) {
        Comment comment = commentService.getCommentById(id);
        Long points = comment.getPoints() + 1;
        User owner = comment.getUser();
        Long userPoints = owner.getPoints() + 1;
        owner.setPoints(userPoints);
        comment.setPoints(points);
        User votingUser = dataHelper.getUserSendToView(principal, model);
        Vote vote = new Vote();
        vote.setUser(votingUser);
        vote.setComment(comment);
        vote.setVoted(2);
        commentService.saveComment(comment);
        voteService.saveVote(vote);
        userService.saveUser(owner);
    }

    @PostMapping("/app/voteddowncomment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteDownComment(@PathVariable Long id, Model model, Principal principal) {
        Comment comment = commentService.getCommentById(id);
        Long points = comment.getPoints() - 1;
        User owner = comment.getUser();
        Long userPoints = owner.getPoints() - 1;
        owner.setPoints(userPoints);
        comment.setPoints(points);
        User votingUser = dataHelper.getUserSendToView(principal, model);
        Vote vote = new Vote();
        vote.setUser(votingUser);
        vote.setComment(comment);
        vote.setVoted(1);
        commentService.saveComment(comment);
        voteService.saveVote(vote);
        userService.saveUser(owner);
    }

}
