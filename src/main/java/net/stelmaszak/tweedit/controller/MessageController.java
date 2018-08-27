package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.service.MessageService;
import net.stelmaszak.tweedit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

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
            model.addAttribute("message", message);
            model.addAttribute("appContext", "message");
        } else {
            return "error";
        }
        model.addAttribute("unread", messageService.getUnreadMessagesByUser(user));
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
