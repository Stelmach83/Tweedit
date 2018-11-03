package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class CustomErrorController implements ErrorController {

    @Autowired
    private DataHelper dataHelper;

    @RequestMapping("/error")
    public String error(Principal principal, Model model) {
        User user = dataHelper.getUserSendToView(principal, model);
        if (user != null) {
            dataHelper.setTodaysDate(model);
            dataHelper.getAllLogs(model);
            dataHelper.getIntegerUnreadMessagesForUser(user, model);
            dataHelper.getAllCategoriesAndSendToView(model);
            dataHelper.getUserVotesSendToView(user, model);
        }
        dataHelper.setAppContext("error", model);
        return "main";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
