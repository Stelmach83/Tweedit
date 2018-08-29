package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
