package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.service.CategoryService;
import net.stelmaszak.tweedit.service.MessageService;
import net.stelmaszak.tweedit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/unread")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    public String howManyUnread() {

        Optional<User> findUser = userService.getUserByEmail("stelmaszak@gmail.com");
        User user = findUser.get();
        int unread = messageService.getUnreadMessagesByUser(user);

        return String.valueOf(unread);
    }

    @RequestMapping("/messages")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showMessages(Model model, Principal principal) {

        User user = findUser(principal, model);
        List<Message> messages = messageService.getMessagesByToUser(user);
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("messages", messages);
        model.addAttribute("appContext", "messages");
        return "main";
    }

    @RequestMapping("/message/{message_id}")
    public String showMessage(Model model, Principal principal, @PathVariable Long message_id) {
        User user = findUser(principal, model);
        List<Message> messages = messageService.getMessagesByToUser(user);
        if (messages.contains(messageService.getMessagyById(message_id))) {
            Message message = messageService.getMessagyById(message_id);
            message.setMessageRead(1);
            messageService.saveMessage(message);
            List<Category> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("message", message);
            model.addAttribute("appContext", "message");
        } else {
            List<Category> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            return "error";
        }
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        return "main";
    }

    @GetMapping("/send")
    public String sendMessage(Model model, Principal principal) {
        User user = findUser(principal, model);
        List<Message> messages = messageService.getMessagesByToUser(user);
        List<Category> categories = categoryService.getCategories();
        List<User> users = userService.getAllUsers();
        Message message = new Message();
        model.addAttribute("message", message);
        model.addAttribute("users", users);
        model.addAttribute("categories", categories);
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
        model.addAttribute("messages", messages);
        model.addAttribute("appContext", "send");
        return "main";
    }

    @PostMapping("/send")
    public String sendPost(Model model, Principal principal, @Valid Message message, BindingResult result) {

        // TODO if (result) wykonaj, else zwróć błędy

        if (result.hasErrors()) {
            User user = findUser(principal, model);
            List<Category> categories = categoryService.getCategories();
            List<Message> messages = messageService.getMessagesByToUser(user);
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("categories", categories);
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("messages", messages);
            model.addAttribute("appContext", "send");
            return "main";
        } else {
            User user = findUser(principal, model);
            List<Category> categories = categoryService.getCategories();
            message.setDate(new Date());
            message.setMessageRead(0);
            messageService.saveMessage(message);
            List<Message> messages = messageService.getMessagesByToUser(user);
            model.addAttribute("categories", categories);
            model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
            model.addAttribute("messages", messages);
            model.addAttribute("appContext", "messages");
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
