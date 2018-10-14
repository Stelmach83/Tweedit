package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Category;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllFromNewest() {
        return postRepository.findAllByOrderByCreatedDesc();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.getOne(id);
    }

    @Override
    public List<Post> getPostsForUserBySubsCats(Set<Category> categories) {
        return postRepository.findAllByCategoryInOrderByCreatedDesc(categories);
    }

    @Override
    public List<Post> getPostForUsersSubs(List<User> userList) {
        return postRepository.findAllByUserInOrderByCreatedDesc(userList);
    }

    @Override
    public List<Post> getPostsByFollowedCatsAndUsers(Set<Category> categories, Set<User> users) {
        return postRepository.findAllByCategoryInOrUserInOrderByCreatedDesc(categories, users);
    }

    @Override
    public List<Post> getPostsByCat(Category category) {
        return postRepository.findAllByCategoryOrderByCreatedDesc(category);
    }


}
