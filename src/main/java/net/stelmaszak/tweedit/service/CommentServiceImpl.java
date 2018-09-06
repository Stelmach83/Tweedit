package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
