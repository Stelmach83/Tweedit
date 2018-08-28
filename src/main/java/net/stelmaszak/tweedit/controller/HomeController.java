package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.service.CategoryService;
import net.stelmaszak.tweedit.service.MessageService;
import net.stelmaszak.tweedit.service.UserService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageService messageService;


    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        List<Category> categories = categoryService.getCategories();
        User user = findUser(principal, model);
        Date date = new Date();
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
