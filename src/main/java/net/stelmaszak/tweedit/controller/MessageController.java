package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import net.stelmaszak.tweedit.service.MessageService;
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

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private DataHelper dataHelper;

    @RequestMapping("/app/messages")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showMessages(Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.getMessagesToUser(user, model);
        model.addAttribute("appContext", "messages");
        return "main";
    }

    @RequestMapping("/app/message/{message_id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showMessage(Model model, Principal principal, @PathVariable Long message_id) {
        User user = dataHelper.getUserSendToView(principal, model);
        List<Message> messages = dataHelper.getMessagesToUser(user, model);
        Message message = dataHelper.showMessage(message_id, model);
        if (dataHelper.doesMessageExist(messages, message)) {
            dataHelper.setMessageReadAndSave(message);
            dataHelper.getAllCategoriesAndSendToView(model);
            model.addAttribute("appContext", "message");
        } else {
            dataHelper.getAllCategoriesAndSendToView(model);
            return "error";
        }
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        return "main";
    }

    @GetMapping("/app/send")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String sendMessage(Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        dataHelper.getMessagesToUser(user, model);
        dataHelper.getAllCategoriesAndSendToView(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.getUsersOtherThanLogged(user, model);
        Message message = new Message();
        model.addAttribute("message", message);
        model.addAttribute("appContext", "send");
        return "main";
    }

    @PostMapping("/app/send")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String sendPost(Model model, Principal principal, @Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            User user = dataHelper.getUserSendToView(principal, model);
            dataHelper.getMessagesToUser(user, model);
            dataHelper.getAllUsersAndSendToView(model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            model.addAttribute("appContext", "send");
            return "main";
        } else {
            User user = dataHelper.getUserSendToView(principal, model);
            message.setDate(new Date());
            message.setMessageRead(0);
            messageService.saveMessage(message);
            dataHelper.getMessagesToUser(user, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            model.addAttribute("appContext", "messages");
            return "main";
        }
    }

}
