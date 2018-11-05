package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        List<Comment> comments;
        if (commentRepository.getAllByPostOrderByDateAsc(post) != null) {
            comments = commentRepository.getAllByPostOrderByDateAsc(post);
            return comments;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Comment> getAllOrderByDate() {
        return commentRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }
}
