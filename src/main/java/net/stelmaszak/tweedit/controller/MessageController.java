package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private DataHelper dataHelper;

    @RequestMapping("/app/messages")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showMessages(Model model, Principal principal) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.getMessagesToUser(user, model);
        dataHelper.setAppContext("messages", model);
        return "main";
    }

    @RequestMapping("/app/message/{message_id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showMessage(Model model, Principal principal, @PathVariable Long message_id) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        List<Message> messages = dataHelper.getMessagesToUser(user, model);
        Message message = dataHelper.showMessage(message_id, model);
        if (dataHelper.doesMessageExist(messages, message)) {
            dataHelper.setMessageReadAndSave(message);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            dataHelper.setAppContext("message", model);
        } else {
            return "error";
        }
        return "main";
    }

    @GetMapping("/app/send")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String sendMessage(Model model, Principal principal) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.getMessagesToUser(user, model);
        dataHelper.getUsersOtherThanLogged(user, model);
        Message message = new Message();
        model.addAttribute("message", message);
        dataHelper.setAppContext("send", model);
        return "main";
    }

    @PostMapping("/app/send")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String sendPost(Model model, Principal principal, @Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            User user = dataHelper.getRequiredHeaderInfo(principal, model);
            dataHelper.getMessagesToUser(user, model);
            dataHelper.getAllUsersAndSendToView(model);
            dataHelper.setAppContext("send", model);
            return "main";
        } else {
            User user = dataHelper.getRequiredHeaderInfo(principal, model);
            dataHelper.setMessageDateAndReadAndSave(message);
            dataHelper.getMessagesToUser(user, model);
            dataHelper.setAppContext("messages", model);
            return "main";
        }
    }

}
