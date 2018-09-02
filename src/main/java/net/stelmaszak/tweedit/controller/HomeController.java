package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.dto.PostDTO;
import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.entity.Vote;
import net.stelmaszak.tweedit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Stream;

@Controller
public class HomeController {

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


    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        List<Category> categories = categoryService.getCategories();
        User user = findUser(principal, model);
        Date date = new Date();
        List<Post> posts = postService.getAllFromNewest();
        List<Vote> userVotes = voteService.getVotedByUser(user);
        List<PostDTO> postDTOS = new ArrayList<>();

        for (Post p : posts) {
            PostDTO pDto = new PostDTO();
            pDto.setPost(p);
            for (Vote v : userVotes) {
                if (v.getPost() == p) {
                    pDto.setVote(v);
                }
            }
            postDTOS.add(pDto);
        }

        List<PostDTO> postDTOS1 = Stream.of(posts)
                .forEach(new::PostDTO());


        model.addAttribute("postdtos", postDTOS);
        model.addAttribute("userVotes", userVotes);
        model.addAttribute("posts", posts);
        model.addAttribute("now", date);
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("categories", categories);
        model.addAttribute("appContext", "index");
        return "main";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal, Model model) {
        if (error != null) {
            return "redirect:error";
        }
        if (logout != null) {
            return "redirect:lout";
        }
        findUser(principal, model);
        List<Category> categories = categoryService.getCategories();
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

    @RequestMapping("/lout")
    public String logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("appContext", "index");
        return "main";
    }


    @RequestMapping("/app")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public String test() {
        return "logged in";
    }

    @GetMapping("/all")
    @ResponseBody
    public String publicView() {
        return "everyone can see this.";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
