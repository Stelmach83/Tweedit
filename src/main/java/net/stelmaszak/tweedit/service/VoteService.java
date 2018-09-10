package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.entity.Vote;

import java.util.List;

public interface VoteService {

    void saveVote(Vote vote);

    List<Vote> getVotedByUser(User user);

}
