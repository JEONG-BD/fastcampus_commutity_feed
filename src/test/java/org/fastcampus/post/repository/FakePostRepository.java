package org.fastcampus.post.repository;

import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;

import java.util.HashMap;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final HashMap<Long, Post> map = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            map.put(post.getId(), post);
            return post;
        }
        long id = map.size() + 1;

        Post newPost = new Post(id, post.getAuthor(), post.getContentObj());
        map.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return map.get(id);
    }
}
