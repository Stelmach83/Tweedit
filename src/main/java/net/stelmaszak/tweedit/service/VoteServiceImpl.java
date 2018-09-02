package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.entity.Vote;
import net.stelmaszak.tweedit.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;


    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotedByUser(User user) {
        return voteRepository.findAllByUser(user);
    }
}
