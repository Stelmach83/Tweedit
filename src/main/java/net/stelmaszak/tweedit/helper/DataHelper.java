package net.stelmaszak.tweedit.helper;

import net.stelmaszak.tweedit.dto.CommentDTO;
import net.stelmaszak.tweedit.dto.PostDTO;
import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataHelper {

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

    public User getUserSendToView(Principal principal, Model model) {
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

    public List<Vote> getUserVotesSendToView(User user, Model model) {
        List<Vote> userVotes = voteService.getVotedByUser(user);
        model.addAttribute("userVotes", userVotes);
        return userVotes;
    }

    public List<Category> getAllCategoriesAndSendToView(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return categories;
    }

    public List<PostDTO> getPostDTOandSendToView(List<Post> posts, User user, Model model) {
        List<PostDTO> postDTOS =
                posts.stream()
                        .map(Post::mapToPostDTO)
                        .map(x -> x.addVote(getUserVotesSendToView(user, model)))
                        .collect(Collectors.toList());
        model.addAttribute("postdtos", postDTOS);
        return postDTOS;
    }

    public List<CommentDTO> getCommentDTOandSendToView(List<Comment> comments, User user, Model model) {
        List<CommentDTO> commentDTOS = comments.stream()
                .map(Comment::mapToCommentDTO)
                .map(x -> x.addVote(getUserVotesSendToView(user, model)))
                .collect(Collectors.toList());
        model.addAttribute("commentsdtos", commentDTOS);
        return commentDTOS;
    }

    public int getIntegerUnreadMessagesForUser(User user, Model model) {
        int unread = (int) messageService.getMessagesByToUser(user).stream()
                .filter(m -> m.getMessageRead() == 0)
                .count();
        model.addAttribute("unread", unread);
        return unread;
    }

}
