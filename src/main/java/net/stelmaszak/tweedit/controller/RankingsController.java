package net.stelmaszak.tweedit.controller;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class RankingsController {

    @Autowired
    private DataHelper dataHelper;

    @GetMapping("/app/showrankings")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String showRankings(Model model, Principal principal) {
        User user = dataHelper.getUserSendToView(principal, model);
        dataHelper.getTop10Users(model);
        dataHelper.setAppContext("rankings", model);
        dataHelper.setTodaysDate(model);
        dataHelper.getIntegerUnreadMessagesForUser(user, model);
        dataHelper.getAllCategoriesAndSendToView(model);
        return "main";
    }
}
