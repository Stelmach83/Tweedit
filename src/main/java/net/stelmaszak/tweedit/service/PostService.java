package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Post;

import java.util.List;

public interface PostService {

    void savePost(Post post);

    List<Post> getAllPosts();

    List<Post> getAllFromNewest();

}
