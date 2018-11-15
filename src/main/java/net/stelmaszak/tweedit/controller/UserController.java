package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/app/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showUser(Model model, Principal principal, @PathVariable Long id) {
        dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.getViewUser(id, model);
        dataHelper.setAppContext("user", model);
        return "main";
    }

    @GetMapping("/app/userprofile")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showUserProfile(Model model, Principal principal) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        if (user != null) {
            dataHelper.getUserVotesSendToView(user, model);
        }
        dataHelper.setAppContext("profile", model);
        return "main";
    }
}
