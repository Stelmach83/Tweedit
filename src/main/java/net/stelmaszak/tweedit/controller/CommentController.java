package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.dto.CommentDTO;
import net.stelmaszak.tweedit.dto.PostDTO;
import net.stelmaszak.tweedit.entity.*;
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
    @Autowired
    private CommentService commentService;

    @GetMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String addComment(Model model, Principal principal, @PathVariable String postId) {
        List<Category> categories = categoryService.getCategories();
        User user = findUser(principal, model);
        Date date = new Date();
        List<Post> posts = postService.getAllFromNewest();
        List<Vote> userVotes = voteService.getVotedByUser(user);
        List<PostDTO> postDTOS = posts.stream()
                .map(Post::mapToPostDTO)
                .map(x -> x.addVote(userVotes))
                .collect(Collectors.toList());
        List<Comment> comments = commentService.getAllComments();
        List<CommentDTO> commentDTOS = comments.stream()
                .map(Comment::mapToCommentDTO)
                .map(x -> x.addVote(userVotes))
                .collect(Collectors.toList());
        model.addAttribute("commentsdtos", commentDTOS);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("addcomment", postId);
        model.addAttribute("postdtos", postDTOS);
        model.addAttribute("userVotes", userVotes);
        model.addAttribute("posts", posts);
        model.addAttribute("now", date);
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("categories", categories);
        model.addAttribute("appContext", "index");
        return "main";
    }

    @PostMapping("/app/addcomment/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postComment(Model model, Principal principal, @Valid Comment comment, BindingResult result, @PathVariable String postId) {

        if (result.hasErrors()) {
            List<Category> categories = categoryService.getCategories();
            User user = findUser(principal, model);
            Date date = new Date();
            List<Post> posts = postService.getAllFromNewest();
            List<Vote> userVotes = voteService.getVotedByUser(user);
            List<PostDTO> postDTOS = posts.stream()
                    .map(Post::mapToPostDTO)
                    .map(x -> x.addVote(userVotes))
                    .collect(Collectors.toList());
            model.addAttribute("postdtos", postDTOS);
            List<Comment> comments = commentService.getAllComments();
            List<CommentDTO> commentDTOS = comments.stream()
                    .map(Comment::mapToCommentDTO)
                    .map(x -> x.addVote(userVotes))
                    .collect(Collectors.toList());
            model.addAttribute("commentsdtos", commentDTOS);
            model.addAttribute("addcomment", postId);
            model.addAttribute("userVotes", userVotes);
            model.addAttribute("now", date);
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("categories", categories);
            model.addAttribute("appContext", "index");
            return "main";
        } else {

            List<Category> categories = categoryService.getCategories();
            User user = findUser(principal, model);
            Date date = new Date();
            List<Post> posts = postService.getAllFromNewest();
            List<Vote> userVotes = voteService.getVotedByUser(user);
            List<PostDTO> postDTOS = posts.stream()
                    .map(Post::mapToPostDTO)
                    .map(x -> x.addVote(userVotes))
                    .collect(Collectors.toList());
            commentService.saveComment(comment);
            List<Comment> comments = commentService.getAllComments();
            List<CommentDTO> commentDTOS = comments.stream()
                    .map(Comment::mapToCommentDTO)
                    .map(x -> x.addVote(userVotes))
                    .collect(Collectors.toList());
            model.addAttribute("commentsdtos", commentDTOS);
            model.addAttribute("postdtos", postDTOS);
            model.addAttribute("userVotes", userVotes);
            model.addAttribute("now", date);
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("categories", categories);
            model.addAttribute("appContext", "index");
            return "main";
        }
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
