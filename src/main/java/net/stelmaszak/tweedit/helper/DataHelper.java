package net.stelmaszak.tweedit.helper;

import net.stelmaszak.tweedit.dto.CommentDTO;
import net.stelmaszak.tweedit.dto.PostDTO;
import net.stelmaszak.tweedit.entity.*;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.*;
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
                user.setLogged(1);
                model.addAttribute("user", user);
                return user;
            }
        }
        return null;
    }

    public User getViewUser(Long id, Model model) {
        User viewUser = userService.getUserById(id);
        model.addAttribute("viewUser", viewUser);
        return viewUser;
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

    public List<Post> getAllPostsFromNewest() {
        return postService.getAllFromNewest();
    }

    public void saveNewUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        user.setJoined(new Date());
        user.setFollowers(0L);
        user.setPoints(0L);
        userService.saveUser(user);
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }

    // TODO refactor
    public List<PostDTO> getPostDTOandSendToView(List<Post> posts, User user, Model model) {
        List<Vote> userVotes = getUserVotesSendToView(user, model);
        List<PostDTO> postDTOS =
                posts.stream()
                        .map(Post::mapToPostDTO)
                        .map(p -> p.addVote(userVotes))
                        .map(p -> p.addComments())
                        .collect(Collectors.toList());
        List<PostDTO> postsWithAllVotes = new ArrayList<>();
        for (PostDTO p : postDTOS) {
            List<CommentDTO> postComments = p.getComments();
            List<CommentDTO> postCommentswithVotes = new ArrayList<>();
            for (CommentDTO c : postComments) {
                c.addUserVote(userVotes);
                postCommentswithVotes.add(c);
            }
            p.setComments(postCommentswithVotes);
            postsWithAllVotes.add(p);
        }
        model.addAttribute("postdtos", postsWithAllVotes);
        return postDTOS;
    }

    public List<Post> getSubPostsFromCats(Principal principal, Model model) {
        User user = getUserSendToView(principal, model);
        Set<Category> categories = user.getCategories();
        List<Post> posts = postService.getPostsForUserBySubsCats(categories);
        return posts;
    }

    public List<Post> getPostsByFollowedCatsAndUsers(Principal principal, Model model) {
        User user = getUserSendToView(principal, model);
        List<Post> posts = postService.getPostsByFollowedCatsAndUsers(user.getCategories(), user.getSubbedToUsers());
        return posts;
    }

    public List<Post> getPostsByCategory(Category category) {
        List<Post> posts = postService.getPostsByCat(category);
        return posts;
    }

    public List<Comment> getAllComments() {
        return commentService.getAllOrderByDate();
    }

    public int getIntegerUnreadMessagesForUser(User user, Model model) {
        int unread = (int) messageService.getMessagesByToUser(user).stream()
                .filter(m -> m.getMessageRead() == 0)
                .count();
        model.addAttribute("unread", unread);
        return unread;
    }

    public List<Message> getMessagesToUser(User user, Model model) {
        List<Message> messages = messageService.getMessagesByToUser(user);
        model.addAttribute("messages", messages);
        return messages;
    }

    public Message showMessage(Long message_id, Model model) {
        Message message = messageService.getMessagyById(message_id);
        model.addAttribute("message", message);
        return message;
    }

    public boolean doesMessageExist(List<Message> messages, Message message) {
        return messages.contains(message);
    }

    public void setMessageReadAndSave(Message message) {
        message.setMessageRead(1);
        messageService.saveMessage(message);
    }

    public void setMessageDateAndReadAndSave(Message message) {
        message.setDate(new Date());
        message.setMessageRead(0);
        messageService.saveMessage(message);
    }

    public void setCreatedAndUserDateAndSavePost(Post post, User user) {
        post.setCreated(new Date());
        post.setUser(user);
        postService.savePost(post);
    }

    public List<User> getUsersOtherThanLogged(User user, Model model) {
        List<User> users = userService.getAllUsersOtherThanLoggedIn(user);
        model.addAttribute("users", users);
        return users;
    }

    public List<User> getAllUsersAndSendToView(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return users;
    }

    public void setAppContext(String appContext, Model model) {
        model.addAttribute("appContext", appContext);
    }

    public void setTodaysDate(Model model) {
        Date date = new Date();
        model.addAttribute("now", date);
    }

    public void saveCommment(Comment comment, User user) {
        comment.setDate(new Date());
        comment.setUser(user);
        commentService.saveComment(comment);
    }

    public List<User> getTop10Users(Model model) {
        List<User> users = userService.getTop10Users();
        model.addAttribute("rankings", users);
        return users;
    }

    public List<User> getTop10UsersByFollowers(Model model) {
        List<User> users = userService.getTop10UserByFollowers();
        model.addAttribute("frankings", users);
        return users;
    }

    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public boolean isUserUnique(String username) {
        Long userCount = userService.isUserUnique(username);
        return userCount > 0 ? false : true;
    }

    public boolean isEmailUnique(String email) {
        Long userCount = userService.isEmailUnique(email);
        return userCount > 0 ? false : true;
    }

}
