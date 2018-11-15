package net.stelmaszak.tweedit.controller;

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
        dataHelper.getRequiredHeaderInfo(principal, model);
        dataHelper.getTop10Users(model);
        dataHelper.getTop10UsersByFollowers(model);
        dataHelper.setAppContext("rankings", model);
        return "main";
    }
}
