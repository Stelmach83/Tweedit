package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> allAllFromNewestForPost(Post post) {
        return commentRepository.getAllByPostOrderByDateDesc(post);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.getOne(id);
    }
}
