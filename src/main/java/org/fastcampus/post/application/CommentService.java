package org.fastcampus.post.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.dto.CommentCreateRequestDto;
import org.fastcampus.post.application.dto.CommentUpdateRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, LikeRepository likeRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CommentCreateRequestDto dto){
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(post, user, dto.content());

        return commentRepository.save(comment);
    }

    public Comment update(CommentUpdateRequestDto dto){
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.commentId());
        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto){
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.postId());

       if(likeRepository.checkLike(comment, user)){
           return;
       }
       comment.like(user);
       likeRepository.like(comment, user);
    }


    public void unlikeComment(LikeRequestDto dto){
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.postId());

        if(likeRepository.checkLike(comment, user)){
           comment.unlike(user);
           likeRepository.unlike(comment, user);
        }
    }
}
