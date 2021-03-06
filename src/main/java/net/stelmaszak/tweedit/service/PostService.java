package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;

import java.util.List;
import java.util.Set;

public interface PostService {

    void savePost(Post post);

    List<Post> getAllPosts();

    List<Post> getAllFromNewest();

    Post getPostById(Long id);

    List<Post> getPostsForUserBySubsCats(Set<Category> categories);

    List<Post> getPostsByCat(Category category);

    List<Post> getPostForUsersSubs(List<User> userList);

    List<Post> getPostsByFollowedCatsAndUsers(Set<Category> categories, Set<User> userList);

}
