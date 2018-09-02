package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.entity.Vote;
import net.stelmaszak.tweedit.repository.UserRepository;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;
    @Autowired
    private VoteService voteService;


    @PostMapping("/app/votedup/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteUp(@PathVariable Long id, Model model, Principal principal) {

        Post votedPost = postService.getPostById(id);
        Long postPoints = votedPost.getPoints() + 1;
        votedPost.setPoints(postPoints);
        User votingUser = findUser(principal, model);
        Vote newVote = new Vote();
        newVote.setUser(votingUser);
        newVote.setPost(votedPost);
        newVote.setVoted(2);
        postService.savePost(votedPost);
        voteService.saveVote(newVote);
    }

    @PostMapping("/app/voteddown/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void voteDown(@PathVariable Long id, Model model, Principal principal) {

        Post votedPost = postService.getPostById(id);
        Long postPoints = votedPost.getPoints() - 1;
        votedPost.setPoints(postPoints);
        User votingUser = findUser(principal, model);
        Vote newVote = new Vote();
        newVote.setUser(votingUser);
        newVote.setPost(votedPost);
        newVote.setVoted(1);
        postService.savePost(votedPost);
        voteService.saveVote(newVote);
    }

    @GetMapping("/showusers")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<User> showUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

//    @PostMapping("/adduser")
//    public User registerUser(@RequestBody User user) {
//        userRepository.save(user);
//        return user;
//    }


    private User findUser(Principal principal, Model model) {
        if (principal != null) {
            Optional<User> findUser = userService.getUserByEmail(principal.getName());
            if (findUser.isPresent()) {
                User user = findUser.get();
                model.addAttribute("user", user);
                return user;
            }
        }
        return null;
    }

}