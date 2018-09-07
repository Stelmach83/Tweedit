package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Post;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> getAllComments();

    List<Comment> allAllFromNewestForPost(Post post);

    Comment getCommentById(Long id);

}
