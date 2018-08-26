package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.service.MessageService;
import net.stelmaszak.tweedit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @RequestMapping("/unread")
    @ResponseBody
    public String howManyUnread() {

        Optional<User> findUser = userService.getUserByEmail("stelmaszak@gmail.com");
        User user = findUser.get();
        int unread = messageService.getUnreadMessagesByUser(user);

        return String.valueOf(unread);
    }

}
