package net.stelmaszak.tweedit.helper;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

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
}
