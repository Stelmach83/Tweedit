package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.dto.PostDTO;
import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CommentController {

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

    @GetMapping("/app/addcomment")
    public String addComment(Model model, Principal principal) {
        List<Category> categories = categoryService.getCategories();
        User user = findUser(principal, model);
        Date date = new Date();
        List<Post> posts = postService.getAllFromNewest();
        List<Vote> userVotes = voteService.getVotedByUser(user);
        List<PostDTO> postDTOS = posts.stream()
                .map(Post::mapToPostDTO)
                .map(x -> x.addVote(userVotes))
                .collect(Collectors.toList());
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("addcomment", "true");
        model.addAttribute("postdtos", postDTOS);
        model.addAttribute("userVotes", userVotes);
        model.addAttribute("posts", posts);
        model.addAttribute("now", date);
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("categories", categories);
        model.addAttribute("appContext", "index");
        return "main";
    }

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
