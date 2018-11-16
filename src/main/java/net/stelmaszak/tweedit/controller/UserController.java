package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import net.stelmaszak.tweedit.validator.UserRegisterValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        User newuser = new User();
        model.addAttribute("newuser", newuser);
        dataHelper.setAppContext("register", model);
        return "main";
    }

    @PostMapping("/register")
    public String postRegister(Model model, Principal principal, @Validated({UserRegisterValidationGroup.class}) User newuser, BindingResult result) {
        if (result.hasErrors()) {
            dataHelper.setAppContext("register", model);
        } else {
            dataHelper.saveNewUser(newuser);
            dataHelper.setAppContext("regsuccess", model);
        }
        return "main";
    }

    @PostMapping("/app/updateprofile")
    public String updateUserProfile(Model model, Principal principal) {
        User user = dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.setAppContext("profile", model);
        return "main";
    }

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
        dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.setAppContext("profile", model);
        return "main";
    }

}
