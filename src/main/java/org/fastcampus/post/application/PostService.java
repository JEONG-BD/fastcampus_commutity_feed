package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.PostCreateRequestDto;
import org.fastcampus.post.application.dto.PostUpdateRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    public PostService(UserService userService,
                       PostRepository postRepository,
                       LikeRepository likeRepository) {

        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    public Post createPost(PostCreateRequestDto dto){
        User author = userService.getUser(dto.userId());
        //PostContent postContent = new PostContent(dto.content());
        Post post = new Post((Long)null, author, dto.content());
        //Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(PostUpdateRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        //post.updatePost(user, dto.content(), dto.state());
        post.updateContent(user, dto.content(), dto.state());

        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        if(likeRepository.checkLike(post, user)){
            return;
        }
        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        if(likeRepository.checkLike(post, user)){
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }


}
